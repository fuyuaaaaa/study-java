package top.fuyuaaa.study;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: fuyuaaa
 * @creat: 2019-03-14 13:49
 */
public class Tesss {

    private static String url = "jdbc:mysql://106.14.169.161:3306/test?useSSL=false&rewriteBatchedStatements=true&useUnicode=true&amp&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "Fuyu.742423672";
    private static ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            4 * 2,
            10,
            300, TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(1024),
            nameThreadFactory,
            new ThreadPoolExecutor.AbortPolicy()
        );

        long beginTime = System.currentTimeMillis();
        CountDownLatch latch = new CountDownLatch(100);
        for (int i = 0; i < 100; i++) {
            threadPool.execute(() -> {
                try {
                    executeBatch();
                } catch (Exception e) {
                    System.out.println("插入数据异常");
                } finally {
                    latch.countDown();
                }
            });
        }
        latch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("插入一千万数据用时：" + (endTime - beginTime) / 1000 + " 秒");
        threadPool.shutdown();
    }

    private static void executeBatch() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "insert into tbl_user(name, province, city, openid, tel) values(?,?,?,?,?)";
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            for (int j = 0; j < 100000; j++) {
                String name = UUID.randomUUID().toString().substring(0, 10);
                String province = UUID.randomUUID().toString().substring(0, 5) + "省";
                String city = UUID.randomUUID().toString().substring(0, 5) + "市";
                ps.setString(1, name);
                ps.setString(2, province);
                ps.setString(3, city);
                ps.setString(4, "openid-" + UUID.randomUUID().toString().substring(0, 5) + "-" + j);
                ps.setString(5, String.valueOf(new Random(10000000000L).nextLong() + 10000000000L));
                ps.addBatch();
            }
            ps.executeBatch();
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
