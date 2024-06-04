// 编写一个Java程序，该程序能够创建一个主窗口，并在该窗口中创建一个菜单栏。
//菜单栏中包含三个菜单项：出库、入库和库存查询。
//导入所需的库和自定义的类
import utils.Utils;
import view.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel mainPanel;
    private EmployeeDisplayPanel employeeDisplayPanel;
    public MainFrame(){
        Connection connection = Utils.getConnection();
        //主窗口属性
        setTitle("商品库存管理系统");
        setSize(600,500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //创建菜单
        JMenuBar menuBar =new JMenuBar();
        setJMenuBar(menuBar);

        // 创建菜单项
        JMenu menu1 = new JMenu("仓库管理");
        JMenuItem menuItem1 = new JMenuItem("出库");
        JMenuItem menuItem2 = new JMenuItem("入库");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        menuBar.add(menu1);

        JMenu menu2 = new JMenu("库存管理");
        JMenuItem menuItem3 = new JMenuItem("库存查询");
        menu2.add(menuItem3);
        menuBar.add(menu2);

        JMenu menu3 = new JMenu("人员管理");
        JMenuItem menuItem4 = new JMenuItem("显示员工信息");
        JMenuItem menuItem5 = new JMenuItem("管理员工");

        menu3.add(menuItem4);
        menu3.add(menuItem5);
        menuBar.add(menu3);

        JMenu menu4 = new JMenu("记录查询");
        JMenuItem menuItem6 = new JMenuItem("入库记录查询");
        JMenuItem menuItem7 = new JMenuItem("出库记录查询");
        menu4.add(menuItem6);
        menu4.add(menuItem7);
        menuBar.add(menu4);

        mainPanel = new JPanel();
        mainPanel.setLayout(new CardLayout());
        add(mainPanel);

        employeeDisplayPanel = new EmployeeDisplayPanel();


        // 为“出库”菜单项添加事件监听器
        menuItem1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new out_store().setVisible(true);
            }
        });

        // 为“入库”菜单项添加事件监听器
        menuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new in_store().setVisible(true);
            }
        });
        // 为“库存查询”菜单项添加事件监听器
        menuItem3.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理库存查询菜单项的点击事件
                new show_store().setVisible(true);
            }
        });
        // 为“显示员工信息”菜单项添加事件监听器
        menuItem4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理人员管理菜单项的点击事件
                mainPanel.add(employeeDisplayPanel, "employeeDisplayPanel");
                employeeDisplayPanel.refreshEmployeeData();
                switchPanel("employeeManagement");
            }
        });

        // 为“管理员工”菜单项添加事件监听器
        menuItem5.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理人员管理菜单项的点击事件
                new managerment_employee().setVisible(true);
            }
        });

        // 为“入库记录查询”菜单项添加事件监听器
        menuItem6.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // 处理入库记录查询菜单项的点击事件
                new in_store_record().setVisible(true);
            }
        });

        // 为“出库记录查询”菜单项添加事件监听器
        menuItem7.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e){
                // 处理出库记录查询菜单项的点击事件
                new out_store_record().setVisible(true);
            }
        });
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // 关闭数据库连接
                Utils.closeConnection(connection);
            }
        });
    }
    private void switchPanel(String panelName) {
        CardLayout cardLayout = (CardLayout) mainPanel.getLayout();
        cardLayout.show(mainPanel, panelName);
    }
    //测试用，接入时请注释掉
    public static void main(String []args){
        Connection connection = Utils.getConnection();

        if (connection != null) {
            // Perform operations
            new MainFrame().setVisible(true);
            // Close the database connection

        } else {
            System.out.println("Failed to connect to the database.");
        }
    }
}
