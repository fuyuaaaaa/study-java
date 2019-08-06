package top.fuyuaaa.study.fromzero;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-05 15:04
 */
public class JdbcTest {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://106.14.169.161:3306/blog?characterEncoding=UTF-8&useSSL=false", "root", "Fuyu.742423672");
        Statement statement = connection.createStatement();
        String sql = "select * from user";
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            System.out.println(resultSet.getString("user_id") + " " + resultSet.getString("user_name"));
        }
        connection.close();
    }
}
