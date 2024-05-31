package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class in_store extends JFrame {
    public  in_store(){
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

        JButton in_btn = new JButton("入库");
        JLabel title = new JLabel("商品入库管理");
        JLabel goods_id = new JLabel("商品号");
        JLabel out_id = new JLabel("入库号");
        JLabel goods_nums = new JLabel("商品入库数量");
        JLabel in_date = new JLabel("入库日期");

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
        p5.add(in_date);
        p5.add(f4);
        p6.add(in_btn);

        add(p1);
        add(p2);
        add(p3);
        add(p4);
        add(p5);
        add(p6);

        in_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"入库成功！","入库",JOptionPane.DEFAULT_OPTION);
            }
        });
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
