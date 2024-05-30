package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import pojo.Employee;
import service.EmployeeService;

public class EmployeeDisplayPanel extends JPanel {
    // 声明私有变量
    private JTable table;
    private DefaultTableModel tableModel;
    private EmployeeService employeeService;

    // 构造函数
    public EmployeeDisplayPanel() {
        employeeService = new EmployeeService();
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        table = new JTable(tableModel);

        // 添加列
        tableModel.addColumn("员工号");
        tableModel.addColumn("员工名字");
        tableModel.addColumn("电话号码");
        tableModel.addColumn("职称");

        // 添加滚动面板
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // 加载员工数据
        loadEmployeeData();
    }

    // 加载员工数据
    private void loadEmployeeData() {
        try {
            List<Employee> employees = employeeService.queryall();
            // 添加数据
            for (Employee employee : employees) {
                tableModel.addRow(new Object[]{
                        employee.getId(),
                        employee.getName(),
                        employee.getPhone_number(),
                        employee.getAuthority()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "加载员工信息失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
    public void refreshEmployeeData() {
        try {
            List<Employee> employees = employeeService.queryall();
            String[] columnNames = {"员工号", "员工名", "电话", "职称"};
            String[][] data = new String[employees.size()][4];
            for (int i = 0; i < employees.size(); i++) {
                Employee employee = employees.get(i);
                data[i][0] = employee.getId();
                data[i][1] = employee.getName();
                data[i][2] = employee.getPhone_number();
                data[i][3] = String.valueOf(employee.getAuthority());
            }
            table.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载员工信息失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
