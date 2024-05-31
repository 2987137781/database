package view;

import pojo.Inbound;
import pojo.Stock;
import service.InboundService;
import service.StockService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


public class in_store extends JFrame {
    public  in_store(){
        // super("出库管理");
        setTitle("商品仓库管理");
        setSize(500,440);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(7,1,5,5));

        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3= new JPanel();
        JPanel p4 = new JPanel();
        JPanel p5 = new JPanel();
        JPanel p6 = new JPanel();
        JPanel p7 = new JPanel();

        JButton in_btn = new JButton("入库");
        JLabel title = new JLabel("商品入库管理");
        JLabel goods_id = new JLabel("商品号");
        JLabel in_id = new JLabel("入库号");
        JLabel goods_nums = new JLabel("商品出入数量");
        JLabel in_date = new JLabel("入库时间");
        JLabel employee_id = new JLabel("员工号");

        JTextField id_f = new JTextField(20);
        JTextField employee_f = new JTextField(20);
        JTextField goods_f = new JTextField(20);
        JTextField num_f = new JTextField(20);
        JTextField time_f = new JTextField(20);
        time_f.setText("(默认当前时间)");

        p1.add(title);
        p2.add(in_id);
        p2.add(id_f);
        p3.add(employee_id);
        p3.add(employee_f);
        p4.add(goods_id);
        p4.add(goods_f);
        p5.add(goods_nums);
        p5.add(num_f);
        p6.add(in_date);
        p6.add(time_f);
        p7.add(in_btn);

//        p1.setSize(500,100);
//        title.setSize(200,100);

        add(p1);
        add(p2);
        add(p7);
        add(p3);
        add(p4);
        add(p5);
        add(p6);
        add(p7);

        time_f.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (time_f.getText().equals("(默认当前时间)")) {
                    time_f.setText(""); // 清空文本框供用户输入
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (time_f.getText().isEmpty()) {
                    time_f.setText("(默认当前时间)"); // 用户未输入内容时恢复提示信息
                }
            }
        });

        in_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Inbound inbound = new Inbound();
                inbound.setId(id_f.getText());
                inbound.setEmployee_id(employee_f.getText());
                inbound.setGoods_id(goods_f.getText());
                inbound.setNum(Integer.parseInt(num_f.getText()));
                int in_num = (Integer.parseInt(num_f.getText()));
                String s1 = goods_f.getText();

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String s = time_f.getText();
                Date date;
                try {
                    InboundService inboundService = new InboundService();
                    StockService stockService = new StockService();

                    Stock stock = stockService.stock_queryid(s1);

                    if(stock.getId() == null){
//
                        JOptionPane.showMessageDialog(null,"商品编号不存在！入库失败！！","入库",JOptionPane.ERROR_MESSAGE);

                    }
                    else {
                        if(!s.equals("(默认当前时间)")){
                            date = simpleDateFormat.parse(s);
                        }
                        else{
                            date = new Date();
                        }

                        Timestamp timestamp=new Timestamp(date.getTime());
                        inbound.setTrade_time(timestamp);
                        inboundService.inbound_insert(inbound);
                        stock.setNum(stock.getNum()+in_num);
                        stockService.stock_updateaut(stock);
                        JOptionPane.showMessageDialog(null,"入库成功！","入库",JOptionPane.DEFAULT_OPTION);
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
