package org.example.view;

import org.example.dao.ScoreDao;
import org.example.domain.Score;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class HomeView extends JFrame {
    private JTable table;

    public HomeView() throws SQLException, ClassNotFoundException {



        setTitle("主页");
        setVisible(true);
        setSize(500, 500);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建表格模型
        DefaultTableModel model = new DefaultTableModel();

        model.addColumn("ID");
        model.addColumn("做题时间");
        model.addColumn("分数");

        ScoreDao scoreDao = new ScoreDao();


        // 添加一些示例数据
        List<String[]> strings = scoreDao.scoreList();
        for (String[] s:strings){
            System.out.println(s);
            model.addRow(s);
        }

        // 创建表格并设置模型
        table = new JTable(model);
        // 添加表格到滚动窗格
        JScrollPane scrollPane = new JScrollPane(table);

        // 将滚动窗格添加到主窗口
        add(scrollPane);

        setLocationRelativeTo(null);  // 居中显示

        JPanel jPanel = new JPanel();
        jPanel.setSize(100,100);
        jPanel.setLayout(new GridLayout(1,3));
        JButton goTestButton = new JButton("去做题");
        JButton exitButton = new JButton("退出");

        JTextField jTextField = new JTextField();

        jPanel.add(goTestButton);
        jPanel.add(jTextField);

        add(exitButton);
        add(jPanel);

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit1();
            }
        });
        goTestButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goArithmeticOperationTest();
            }
        });
    }
    private void exit1(){
        this.dispose();

    }
    private void goArithmeticOperationTest(){
        this.dispose();
        new ArithmeticOperationTest();

    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new HomeView().setVisible(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
