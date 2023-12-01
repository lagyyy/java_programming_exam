import org.example.dao.ScoreDao;
import org.example.dao.UserDao;
import org.example.util.JDBCUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseConnector {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Connection connector = JDBCUtil.getConnector();
        Statement statement = connector.createStatement();
        ResultSet rs = statement.executeQuery("SELECT id, name, password FROM user "+"where name=123 and password=123");
        if (rs.next()){
            System.out.println("存在该用户");
        }

        UserDao userDao = new UserDao();
        boolean register = userDao.register("test", "test");
        System.out.println(register);

        ScoreDao scoreDao = new ScoreDao();
         scoreDao.scoreList();


//        while(rs.next()){
//            // 通过字段检索
//            int id  = rs.getInt("id");
//            String name = rs.getString("name");
//            String url = rs.getString("password");
//
//            // 输出数据
//            System.out.print("ID: " + id);
//            System.out.print(", 站点名称: " + name);
//            System.out.print(", 站点 URL: " + url);
//            System.out.print("\n");
//        }

    }
}
