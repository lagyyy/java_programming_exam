package org.example.dao;

import cn.hutool.core.date.DateTime;
import org.example.util.JDBCUtil;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ScoreDao {
    public List<String []> scoreList() throws SQLException, ClassNotFoundException {
        Connection connector = JDBCUtil.getConnector();
        Statement statement = connector.createStatement();
        String sql = String.format("SELECT * FROM score");
        ResultSet rs = statement.executeQuery(sql);

        ArrayList<String []> scoreArrayList = new ArrayList<>();

        while (rs.next()){
            String[] objects = new String[3];
            System.out.println("存在数据");
            objects[0]  = rs.getString("id");
            objects[1] = rs.getString("create_time");
            objects[2] = rs.getString("score");
            scoreArrayList.add(objects);
        }
        return scoreArrayList;
    }

    public boolean addScore(DateTime dateTime, int score) throws SQLException, ClassNotFoundException {
        Connection connector = JDBCUtil.getConnector();
        Statement statement = connector.createStatement();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedTime = dateFormat.format(dateTime);

        String sql = String.format("INSERT INTO score (create_time, score) VALUES ('%s', '%d')", formattedTime, score);
        int i = statement.executeUpdate(sql);
//        ResultSet rs = (ResultSet) i
        System.out.println(i);
        if (i==1){
            return true;
        }
        return false;
    }
}
