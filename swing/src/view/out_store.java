package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class out_store extends JFrame {
    public  out_store(){
       // super("出库管理");
        setTitle("商品仓库管理");
        setSize(500,440);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(6,1,10,10));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3= new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();

        JButton out_btn = new JButton("出库");
        JLabel title = new JLabel("商品出库管理");
        JLabel goods_id = new JLabel("商品号");
        JLabel out_id = new JLabel("出库号");
        JLabel goods_nums = new JLabel("商品出库数量");
        JLabel out_date = new JLabel("出库日期");

        JTextField f1 = new JTextField(20);
        JTextField f2 = new JTextField(20);
        JTextField f3 = new JTextField(20);
        JTextField f4 = new JTextField(20);

        p1.add(title);
        p2.add(out_id);
        p2.add(f1);
        p3.add(goods_id);
        p3.add(f2);
        p4.add(goods_nums);
        p4.add(f3);
        p5.add(out_date);
        p5.add(f4);
        p6.add(out_btn);

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);

        out_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"出库成功！","出库",JOptionPane.DEFAULT_OPTION);
            }
        });
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

}
