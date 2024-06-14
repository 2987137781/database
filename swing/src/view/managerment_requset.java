package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import service.OutstoreRequestService;
import pojo.OutstoreRequest;
import java.util.List;

public class managerment_requset extends JFrame {
    private JButton approveRequestButton;
    private JTable requestTable;
    private DefaultTableModel tableModel;

    public managerment_requset() {
        setTitle("管理出库请求");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // 初始化表格
        String[] columnNames = {"请求ID", "员工号", "商品号", "数量"};
        tableModel = new DefaultTableModel(columnNames, 0);
        requestTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(requestTable);
        add(scrollPane);

        // 加载待处理的请求
        loadPendingRequests();

        // 批准按钮
        approveRequestButton = new JButton("批准出库请求");
        approveRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = requestTable.getSelectedRow();
                if (selectedRow != -1) {
                    String requestId = (String) tableModel.getValueAt(selectedRow, 0);
                    try {
                        OutstoreRequestService.approveOutstoreRequest(requestId, true);
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "出库请求已批准");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "批准出库请求失败");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请选择一个请求");
                }
            }
        });
        add(approveRequestButton);

        // 拒绝按钮
        JButton denyRequestButton = new JButton("拒绝出库请求");
        denyRequestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = requestTable.getSelectedRow();
                if (selectedRow != -1) {
                    String requestId = (String) tableModel.getValueAt(selectedRow, 0);
                    try {
                        OutstoreRequestService.approveOutstoreRequest(requestId, false);
                        tableModel.removeRow(selectedRow);
                        JOptionPane.showMessageDialog(null, "出库请求已拒绝");
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "拒绝出库请求失败");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "请选择一个请求");
                }
            }
        });
        add(denyRequestButton);
    }

    // 加载待处理请求
    private void loadPendingRequests() {
        // 获取待处理请求
        List<OutstoreRequest> requests = OutstoreRequestService.getPendingRequests();
        // 遍历请求
        for (OutstoreRequest request : requests) {
            // 将请求添加到表格模型中
            tableModel.addRow(new Object[]{request.getRequestId(), request.getEmployeeId(), request.getProductId(), request.getQuantity()});
        }
    }

    // 测试
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new managerment_requset().setVisible(true);
            }
        });
    }
}
