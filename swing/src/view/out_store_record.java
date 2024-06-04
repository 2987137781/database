package view;


import pojo.Inbound;
import pojo.Outbound;
import pojo.Stock;
import service.InboundService;
import service.OutboundService;
import service.StockService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import pojo.Outbound;
import service.OutboundService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

//购物车
public class out_store_record extends JFrame {
    //String username;
    public out_store_record(){
        //    this.username=username;
        //初始化界面
        initJFrame();
        //在这个界面添加内容
        initView();
        //让页面展示出来
        this.setVisible(true);

    }

    private void initView() {


        // 创建一个3行3列的默认数据模型
        DefaultTableModel model = new DefaultTableModel();

        // 如果需要可以给表格添加列名
        model.setColumnIdentifiers(new String[]{"出库号", "员工号","商品号","数量","出库时间"});

        // 创建一个JTable实例并应用模型
        JTable table = new JTable(model);
        table.setEnabled(false);    //表中数据不可更改

        //测试用
//        for(int i=1;i<10;i++){
//            String s[] = {"a"+i,"b"+i,"100"};
//            model.addRow(s);
//        }

        // 使用JScrollPane包装表格以自动处理滚动条
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 50, 660, 350); // 设置滚动面板的位置和大小
        this.getContentPane().add(scrollPane); // 将滚动面板添加到窗体的内容窗格中


        JButton searchButton2=new JButton();
        searchButton2.setBounds(200,10,130,30);
        searchButton2.setText("查询全部");
        this.getContentPane().add(searchButton2);
        searchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    //清除已查询的结果
                    while(model.getRowCount()!=0){
                        model.removeRow(model.getRowCount()-1);
                    }

                    //StockService stockService = new StockService();
                    //List<Stock> list = stockService.queryall();
                    OutboundService outboundService = new OutboundService();
                    List<Outbound> list = outboundService.queryall();

                    for(int i=0;i<list.size();i++){
                        //System.out.println(list.get(i));
                        String s[] = {list.get(i).getId(),list.get(i).getEmployee_id(),list.get(i).getGoods_id(), String.valueOf(list.get(i).getNum()), String.valueOf(list.get(i).getTrade_time())};
                        model.addRow(s);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //添加返回主界面按钮
        JButton ReturnButton=new JButton();
        ReturnButton.setBounds(350,10,130,30);
        ReturnButton.setText("返回主界面");
        this.getContentPane().add(ReturnButton);
        //添加监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                out_store_record.this.setVisible(false);
            }
        });

    }

    private void initJFrame() {
        this.setTitle("查看出库记录");
        this.setSize(700, 500);
        //设置页面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //取消按钮等元素居中放置
        this.setLayout(null);
    }
   // public static void main(String []args){ new out_store_record();}
}


