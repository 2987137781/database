package view;

import pojo.Stock;
import service.StockService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

//购物车
public class show_store extends JFrame {
    //String username;
    public show_store(){
    //    this.username=username;
        //初始化界面
        initJFrame();
        //在这个界面添加内容
        initView();
        //让页面展示出来
        this.setVisible(true);

    }

    private void initView() {

        //添加搜索输入框
        JTextField searchText = new JTextField("（请输入商品编号）", 10);
        searchText.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (searchText.getText().equals("（请输入商品编号）")) {
                    searchText.setText(""); // 清空文本框供用户输入
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (searchText.getText().isEmpty()) {
                    searchText.setText("（请输入商品编号）"); // 用户未输入内容时恢复提示信息
                }
            }
        });
        searchText.setBounds(30,10,130,30);
        this.getContentPane().add(searchText);

        // 创建一个3行3列的默认数据模型
        DefaultTableModel model = new DefaultTableModel();

        // 如果需要可以给表格添加列名
        model.setColumnIdentifiers(new String[]{"商品号", "商品名","库存数量"});

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

        //添加查询按钮
        JButton searchButton=new JButton();
        searchButton.setBounds(170,10,80,30);
        searchButton.setText("查询");
        this.getContentPane().add(searchButton);
        //添加监听
        //.....
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    StockService stockService = new StockService();
                    String s1 = searchText.getText();
                    Stock stock= stockService.stock_queryid(s1);
                    if(stock.getId() == null){
                        JOptionPane.showMessageDialog(null,"商品编号不存在！请重新输入！！","库存",JOptionPane.ERROR_MESSAGE);

                    }
                    else{
                        String s[] = {stock.getId(),stock.getName(),String.valueOf(stock.getNum())};
                        model.addRow(s);
                    }

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        JButton searchButton2=new JButton();
        searchButton2.setBounds(330,10,110,30);
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

                    StockService stockService = new StockService();
                    List<Stock> list = stockService.queryall();
                    for(int i=0;i<list.size();i++){
                        System.out.println(list.get(i));
                        String s[] = {list.get(i).getId(),list.get(i).getName(),String.valueOf(list.get(i).getNum())};
                        model.addRow(s);
                    }
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        //添加返回主界面按钮
        JButton ReturnButton=new JButton();
        ReturnButton.setBounds(530,10,130,30);
        ReturnButton.setText("返回主界面");
        this.getContentPane().add(ReturnButton);
        //添加监听
        ReturnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                show_store.this.setVisible(false);
            }
        });

    }

    private void initJFrame() {
        this.setTitle("查看库存");
        this.setSize(700, 500);
        //设置页面居中
        this.setLocationRelativeTo(null);
        //设置关闭模式
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //取消按钮等元素居中放置
        this.setLayout(null);
    }

}


