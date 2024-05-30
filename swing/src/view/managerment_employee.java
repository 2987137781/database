package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import pojo.Employee;
import service.EmployeeService;
public class managerment_employee extends JFrame {
    public managerment_employee(){
        EmployeeService employeeService = new EmployeeService();
        setTitle("员工管理");
        setSize(500,440);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6,1,10,10));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3= new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();

        JButton add_btn = new JButton("添加员工");
        JButton delete_btn = new JButton("删除员工");
        JLabel title = new JLabel("员工");
        JLabel employee_id = new JLabel("员工号");
        JLabel employee_name = new JLabel("员工名");
        JLabel phone_number = new JLabel("电话");

        JTextField f1 = new JTextField(20);
        JTextField f2 = new JTextField(20);
        JTextField f3 = new JTextField(20);

        p1.add(title);
        p2.add(employee_id);
        p2.add(f1);
        p3.add(employee_name);
        p3.add(f2);
        p4.add(phone_number);
        p4.add(f3);
        p5.add(add_btn);
        p6.add(delete_btn);

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);

        add_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = f1.getText();
                    String name = f2.getText();
                    String phoneNumber = f3.getText();

                    Employee employee = new Employee();
                    employee.setId(id);
                    employee.setName(name);
                    employee.setPhone_number(phoneNumber);
                    employee.setAuthority(0);
                    int result = employeeService.employee_insert(employee);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "添加成功！", "信息", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "添加失败！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "操作失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        delete_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String id = f1.getText();

                    int result = employeeService.employee_delete(id);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "删除成功！", "信息", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "删除失败！", "错误", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "操作失败: " + ex.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public static void main(String[] args) {
        new managerment_employee();
    }
}
