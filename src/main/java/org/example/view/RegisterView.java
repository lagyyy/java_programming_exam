package org.example.view;

import org.example.dao.UserDao;
import org.example.util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class RegisterView extends JFrame {
    private JLabel a = new JLabel("请您输入账号"); //账号
    private JLabel b = new JLabel("请您输入密码");
    private JLabel b1 = new JLabel("输入确认密码");
    private JTextField name =  new JTextField();
    private JTextField password = new JTextField();
    private JTextField password1 = new JTextField();

    private JButton loginButton = new JButton("登录");
    private JButton register = new JButton("注册");;
    private JButton exit =new JButton("退出");

    public RegisterView(){
        setTitle("注册");
        setLayout(null);


        Panel panel = new Panel();
        panel.setBounds(20,20,300,140);
        panel.setLayout(new GridLayout(4,2));
        panel.add(a);
        panel.add(name);
        panel.add(b);
        panel.add(password);
        panel.add(b1);
        panel.add(password1);



        add(panel);

        JFrame frame = this;


        loginButton.setBounds(40,250,60,40);
        register.setBounds(140,250,60,40);
        exit.setBounds(240,250,60,40);

        add(loginButton);
        add(register);
        add(exit);

        ScreenUtil.setCenter(this,350,350);
        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openLoginView();
            }
        });

        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit1();
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nameText = name.getText();
                String passwordText = password.getText();
                String password1Text = password1.getText();
                if (!password1Text.equals(passwordText)){
                    JOptionPane.showMessageDialog(frame, "两次密码输入不同",
                            "警告", JOptionPane.WARNING_MESSAGE);
                }else {
                    UserDao userDao = new UserDao();
                    try {
                        boolean register1 = userDao.register(nameText, password1Text);
                        if (!register1){
                            JOptionPane.showMessageDialog(frame, "注册失败",
                                    "警告", JOptionPane.WARNING_MESSAGE);
                        }else {
                            JOptionPane.showMessageDialog(frame, "注册成功",
                                    "警告", JOptionPane.WARNING_MESSAGE);
                            openLoginView();
                        }
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
    }


    private void openLoginView() {
        new LoginView().setVisible(true);
        // 关闭当前窗口
        this.dispose();
    }
    private void exit1(){
        this.dispose();

    }

}
