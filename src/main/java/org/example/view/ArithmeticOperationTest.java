package org.example.view;

import cn.hutool.core.date.DateTime;
import org.example.dao.ScoreDao;
import org.example.view.HomeView;
import org.example.util.ScreenUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ArithmeticOperationTest extends JFrame{
    private static List<JTextField> answerFieldList;
    private static List<JLabel> questionLabel, resultLabel;
    private static int [] answers = new int[10];

    private static int score;

    private static JLabel scoreResult;

    ArithmeticOperationTest(){
        this.dispose();
        answerFieldList = new ArrayList<>();
        resultLabel = new ArrayList<>();
        setVisible(true);
        setTitle("算数测试");
//        ScreenUtil.setCenter(this,600,500);
        setSize(600,500);
// 设置居中显示
        setLocationRelativeTo(null);
        String[] strings = {"+","-"};
        setLayout(new GridLayout(12,3));
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int num1 = random.nextInt(100);
            int num2 = random.nextInt(100);
            JLabel jLabel = new JLabel();
            /**
             * 随机生成+或者-
             * */
            int i1 = random.nextInt(2);
            String string = strings[i1];
            System.out.println(string);
            System.out.println(num1+string+num2);
            jLabel.setText("题目:"+num1+string+num2+"=");
            add(jLabel);
            JLabel jLabel1 = new JLabel();
            if (string.equals("+")){
                jLabel1.setText(num1+num2+"");
                answers[i] = num1+num2;
            }else {
                jLabel1.setText(num1-num2+"");
                answers[i] = num1-num2;
            }
            resultLabel.add(jLabel1);
//            jLabel1.setVisible(false);
            add(jLabel1);

            /**
             * 将生成的答案放在数组中
             * */


            JTextField jTextField = new JTextField();
            add(jTextField);
            answerFieldList.add(jTextField);

        }
        System.out.println(answerFieldList.size());
        System.out.println(answerFieldList.get(0));
        JButton submit = new JButton("提交");
        JButton homeView = new JButton("首页");
        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (submit.getText().equals("提交")){
                    for (int i = 0; i < 10; i++) {
                        JTextField jTextField = answerFieldList.get(i);
                        System.out.println(jTextField.getText());
                        JLabel jLabel = resultLabel.get(i);
                        String text = jTextField.getText();
                        int i1 = Integer.parseInt(text);
                        if (answers[i]==i1){
                            jLabel.setText("✔");
                            score +=10;
                        }else {
                            jLabel.setText("❌,正确答案为:"+answers[i]);
                        }
                        jLabel.setVisible(true);
                    }
                    submit.setText("重做");
                    scoreResult.setVisible(true);
                    scoreResult.setText("总得分为:"+score);
                    ScoreDao scoreDao = new ScoreDao();
                    try {
                        scoreDao.addScore(DateTime.now(),score);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    } catch (ClassNotFoundException ex) {
                        throw new RuntimeException(ex);
                    }

                }else {
                    score = 0;
                    submit.setText("提交");
                    reTest();
                }
            }
        });
        homeView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    goHomeView();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                } catch (ClassNotFoundException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        scoreResult = new JLabel();
        scoreResult.setVisible(false);
        add(scoreResult);
        add(submit);
        add(homeView);
    }
    private void reTest(){
        this.dispose();
        new ArithmeticOperationTest();
    }
    private void goHomeView() throws SQLException, ClassNotFoundException {
        new HomeView();
        this.dispose();

    }

    public static void main(String[] args) {
        new ArithmeticOperationTest();
    }
}
