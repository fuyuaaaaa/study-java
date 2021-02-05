package top.fuyuaaa.study;

/**
 * @author : fuyuaaa
 * @date : 2021-01-05 20:40
 */

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.google.gson.Gson;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import com.aliyuncs.ons.model.v20190214.*;

public class test222 {

    private static String url = "jdbc:mysql://localhost:3306/local?useSSL=false&rewriteBatchedStatements=true&useUnicode=true&amp&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "Fuyu.742423672";

    /**
     * com.mysql.jdbc.exceptions.jdbc4.MySQLNonTransientConnectionException: Data source rejected establishment of connection,  message from server: "Too many connections"
     * 	at sun.reflect.GeneratedConstructorAccessor52.newInstance(Unknown Source)
     * 	at sun.reflect.DelegatingConstructorAccessorImpl.newInstance(DelegatingConstructorAccessorImpl.java:45)
     * 	at java.lang.reflect.Constructor.newInstance(Constructor.java:423)
     */
    public static void main(String[] args) throws SQLException, InterruptedException {
        for (int i = 0; i < 200; i++) {
            new Thread(() -> {
                try {
                    Connection connection = DriverManager.getConnection(url, user, password);
                    System.out.println("创建连接成功" + connection.toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }).start();
        }

        Thread.sleep(100000);
    }
}

