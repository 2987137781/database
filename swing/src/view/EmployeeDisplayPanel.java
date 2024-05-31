package view;

import pojo.Employee;
import service.EmployeeService;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class EmployeeDisplayPanel extends JPanel {
    private JTable employeeTable;
    private EmployeeService employeeService;

    public EmployeeDisplayPanel() {
        employeeService = new EmployeeService();
        setLayout(new BorderLayout());

        employeeTable = new JTable();
        add(new JScrollPane(employeeTable), BorderLayout.CENTER);

        refreshEmployeeData();
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
            employeeTable.setModel(new javax.swing.table.DefaultTableModel(data, columnNames));
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "加载员工信息失败: " + e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
        }
    }
}
