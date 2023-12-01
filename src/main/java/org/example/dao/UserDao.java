package org.example.dao;

import org.example.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDao {
    public boolean login(String name,String password) throws SQLException, ClassNotFoundException {
        Connection connector = JDBCUtil.getConnector();
        Statement statement = connector.createStatement();
        String sql = String.format("SELECT * FROM user WHERE name = '%s' AND password = '%s'", name, password);
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()){
            System.out.println("存在该用户");
            return true;
        }
        return false;
    }
    public boolean register(String name,String password) throws SQLException, ClassNotFoundException {
        Connection connector = JDBCUtil.getConnector();
        Statement statement = connector.createStatement();
        String sql = String.format("INSERT INTO user (name, password) VALUES ('%s', '%S')", name, password);
        int i = statement.executeUpdate(sql);
//        ResultSet rs = (ResultSet) i
        System.out.println(i);
        if (i==1){
            return true;
        }
        return false;
    }
}
