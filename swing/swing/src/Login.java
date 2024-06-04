import service.EmployeeService;
import utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JFrame {
    private JPanel LoginPanel;


    public Login(){
        this.init();
        this.setVisible(true);
    }
    public void init(){
        //this.setContentPane(LoginPanel);
        this.setTitle("登录");
        this.setSize(600, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        JTabbedPane tabbedPane = new JTabbedPane();
        JPanel jp = new JPanel();
        jp.setLayout(null);
        initLoginTab();

        tabbedPane.addTab("登录", LoginPanel);
        this.add(tabbedPane);
    }
    private void initLoginTab(){
        LoginPanel = new JPanel();
        LoginPanel.setLayout(null);
        //手机号 label
        JLabel lblphoneNumber = new JLabel("手机号");
        lblphoneNumber.setSize(70, 30);
        lblphoneNumber.setLocation(100, 40);
        lblphoneNumber.setFont(new Font("黑体", Font.PLAIN, 20));
        //手机号 Jtextfield
        JTextField lblphoneNuberText = new JTextField();
        lblphoneNuberText.setSize(200, 30);
        lblphoneNuberText.setLocation(170, 40);
        //密码 label
        JLabel lblPassword = new JLabel("密 码");
        lblPassword.setSize(70,40);
        lblPassword.setLocation(100, 100);
        lblPassword.setFont(new Font("黑体", Font.PLAIN, 20));
        //密码 JPasswordfield
        JTextField lblPasswordText = new JPasswordField();
        lblPasswordText.setSize(200, 30);
        lblPasswordText.setLocation(170, 105);//很奇怪，对齐之后是105

        //登录按钮 jbutton
        JButton btnLogin = new JButton("登录");
        btnLogin.setSize(100, 30);
        btnLogin.setLocation(200, 250);
        btnLogin.setFont(new Font("黑体", Font.PLAIN, 20));


        //添加组件
        LoginPanel.add(lblphoneNumber);
        LoginPanel.add(lblphoneNuberText);
        LoginPanel.add(lblPassword);
        LoginPanel.add(lblPasswordText);
        LoginPanel.add(btnLogin);

        LoginActionListener loginActionListener=new LoginActionListener();
        loginActionListener.setLblphoneNuberText(lblphoneNuberText);
        loginActionListener.setLblPasswordText(lblPasswordText);
        loginActionListener.setLogin(this);
        btnLogin.addActionListener(loginActionListener);
    }
    private class LoginActionListener implements  ActionListener {
        JTextField lblphoneNuberText;
        JTextField lblPasswordText;
        int i;
        Login login;

        public void setLogin(Login login) {
            this.login = login;
        }

        public int getI() {
            return i;
        }

        public void setLblPasswordText(JTextField lblPasswordText) {
            this.lblPasswordText = lblPasswordText;
        }

        public void setLblphoneNuberText(JTextField lblphoneNuberText) {
            this.lblphoneNuberText = lblphoneNuberText;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            String phone_number=lblphoneNuberText.getText();
            String password=lblPasswordText.getText();
            EmployeeService employeeService=new EmployeeService();
            try {
                this.i=employeeService.query_login(phone_number,password);
                if(i==2) {
                    new MainFrame().setVisible(true);
                    login.setVisible(false);
                }
                else if(i==1){
                    JFrame jFrame=new JFrame("提示");
                    jFrame.setSize(300,200);
                    jFrame.setLocationRelativeTo(null);
                    JPanel tip=new JPanel();
                    tip.setLayout(new GridBagLayout());
                    JLabel jLabel = new JLabel("密码错误");
                    jLabel.setSize(70, 30);
                    jLabel.setFont(new Font("黑体", Font.PLAIN, 20));
                    tip.add(jLabel);
                    jFrame.setContentPane(tip);
                    jFrame.setVisible(true);
                }
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
