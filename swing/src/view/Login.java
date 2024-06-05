package view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
//废弃
public class Login extends JFrame {
    private JPanel LoginPanel;

    public Login(){
        init();
        this.setVisible(true);
    }
    private void init(){
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
        //验证码 label
        JLabel lblVerificationCode = new JLabel("验证码");
        lblVerificationCode.setSize(70, 30);
        lblVerificationCode.setLocation(100, 180);
        lblVerificationCode.setFont(new Font("黑体", Font.PLAIN, 20));
        //验证码 Jtextfield
        JTextField lblVerificationCodeText = new JTextField();
        lblVerificationCodeText.setSize(100, 30);
        lblVerificationCodeText.setLocation(170, 180);

        //验证码显示 label
        JLabel lblVerificationCodeShow = new JLabel("1234");
        lblVerificationCodeShow.setSize(70, 30);
        lblVerificationCodeShow.setLocation(350, 180);
        lblVerificationCodeShow.setFont(new Font("黑体", Font.PLAIN, 20));
        lblVerificationCodeShow.setForeground(Color.red);
        lblVerificationCodeShow.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lblVerificationCodeShow.addMouseListener(
                                new MouseAdapter() {
                                    //代填
                                }
        );
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
        LoginPanel.add(lblVerificationCode);
        LoginPanel.add(lblVerificationCodeText);
        LoginPanel.add(lblVerificationCodeShow);
        LoginPanel.add(btnLogin);
    }
    //测试用的
}
