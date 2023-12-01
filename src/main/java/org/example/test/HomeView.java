package org.example.test;

import org.example.dao.ScoreDao;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class HomeView extends JFrame {
    private JTable table;

    public HomeView() throws SQLException, ClassNotFoundException {
        setTitle("主页");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 创建表格模型
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("id");
        model.addColumn("做题时间");
        model.addColumn("分数");

        // 添加一些示例数据

        ScoreDao scoreDao = new ScoreDao();
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
        add(scrollPane, BorderLayout.CENTER);

        setLocationRelativeTo(null);  // 居中显示
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
