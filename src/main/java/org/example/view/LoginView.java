package org.example.view;



import org.example.dao.UserDao;
import org.example.util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LoginView extends JFrame {
    private JButton loginButton;
    private JButton register;
    public LoginView() throws HeadlessException {
        setSize(550,400);
        setTitle("登录");
        setVisible(true);
        GridLayout gridLayout = new GridLayout();
        gridLayout.setRows(2);
        setLayout(gridLayout);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        panel.setLayout(null);
        JLabel usernameJLabel = new JLabel("用户名:");
        JLabel passwordJlLabel = new JLabel("密\t码:");
        usernameJLabel.setBounds(120, 80, 50, 30);
        passwordJlLabel.setBounds(120, 140, 50, 30);
        panel.add(usernameJLabel);
        panel.add(passwordJlLabel);


        JTextField name = new JTextField(30);
        JTextField password = new JTextField(30);

        name.setBounds(210, 80, 180, 30);
        password.setBounds(210, 140, 180, 30);

        panel.add(name);
        panel.add(password);
        add(panel);

        Panel panel1 = new Panel();
        panel1.setLayout(null);
        loginButton = new JButton("登录");
        register = new JButton("注册");
        loginButton.setBounds(190,20,60,40);
        register.setBounds(330,20,60,40);
        panel1.add(loginButton);
        panel1.add(register);

        add(panel1);

        JFrame frame = this;

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameText = name.getText();
                String passwordText = password.getText();
                UserDao userDao = new UserDao();
                try {
                    boolean login = userDao.login(nameText, passwordText);
                    if (login){
                        JOptionPane.showMessageDialog(frame, "登录成功",
                                "警告", JOptionPane.WARNING_MESSAGE);
                        openHomePageView();
                    }else {
                        System.out.println("该用户不存在");
                        JOptionPane.showMessageDialog(frame, "账号或密码错误！",
                                "警告", JOptionPane.WARNING_MESSAGE);
                        name.setText("");
                        password.setText("");

                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openRegisterView();
            }
        });

        ScreenUtil.setCenter(this,500,500);
    }
    private void openRegisterView() {
        new RegisterView().setVisible(true);
        // 关闭当前窗口
        this.dispose();
    }

    private void openHomePageView() throws SQLException, ClassNotFoundException {
        new HomeView().setVisible(true);
        // 关闭当前窗口
        this.dispose();
    }




    public static void main(String[] args) {
        new LoginView().setVisible(true);
    }

}
