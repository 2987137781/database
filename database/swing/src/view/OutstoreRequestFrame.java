package view;

import service.OutstoreRequestService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OutstoreRequestFrame extends JFrame {
    private JTextField employeeIdField;
    private JTextField productIdField;
    private JTextField quantityField;
    private JButton submitButton;

    public OutstoreRequestFrame() {
        // 设置窗口属性
        setTitle("商品仓库管理");
        setSize(500, 440);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7, 1, 10, 10));

        // 创建面板
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();

        // 创建标签和按钮
        JLabel title = new JLabel("商品出库管理");
        JLabel goodsIdLabel = new JLabel("商品号");
        JLabel outIdLabel = new JLabel("出库号");
        JLabel goodsNumsLabel = new JLabel("商品出库数量");
        JLabel outDateLabel = new JLabel("出库时间");
        JLabel employeeIdLabel = new JLabel("员工号");
        submitButton = new JButton("提交出库请求");

        // 创建文本字段
        employeeIdField = new JTextField(20);
        productIdField = new JTextField(20);
        quantityField = new JTextField(20);
        JTextField timeField = new JTextField(20);
        timeField.setText("(默认当前时间)");

        // 将组件添加到面板
        p1.add(title);
        p2.add(outIdLabel);
        p2.add(new JTextField(20));  // 出库号字段（此处假设由系统生成，不需要用户输入）
        p3.add(employeeIdLabel);
        p3.add(employeeIdField);
        p4.add(goodsIdLabel);
        p4.add(productIdField);
        p5.add(goodsNumsLabel);
        p5.add(quantityField);
        p6.add(outDateLabel);
        p6.add(timeField);
        p7.add(submitButton);

        // 将面板添加到窗口
        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
        add(p7);

        // 设置窗口可见性和默认关闭操作
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // 添加事件监听器到提交按钮
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String employeeId = employeeIdField.getText();
                String productId = productIdField.getText();
                int quantity;
                try {
                    quantity = Integer.parseInt(quantityField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "数量必须是一个有效的数字。", "输入错误", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // 调用服务类提交出库请求
                OutstoreRequestService.submitOutstoreRequest(employeeId, productId, quantity);
                JOptionPane.showMessageDialog(null, "出库请求提交成功，等待经理审批。");
            }
        });
    }
}