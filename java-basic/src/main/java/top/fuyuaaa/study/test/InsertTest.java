package top.fuyuaaa.study.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Date;

import com.mysql.jdbc.PreparedStatement;

/**
 * @author fuyuaaa
 */
public class InsertTest {

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        final String url = "jdbc:mysql://127.0.0.1/management";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "123456";
        Connection conn = null;
        Class.forName(name);
        conn = DriverManager.getConnection(url, user, password);
        if (conn != null) {
            System.out.println("获取连接成功");
            insert(conn);
        } else {
            System.out.println("获取连接失败");
        }

    }

    public static void insert(Connection conn) {
        // 开始时间
        Long begin = System.currentTimeMillis();
        // sql前缀
        String prefix = "INSERT INTO `follow`" +
                "(`id`, `customer_id`, `operator`, `shop_code`, `text`, `communication_type`, `customer_level`, `return_visit_date`, `follow_car_id`, `arrive_moment`, `is_arrive`, `status`, `date_create`, `date_update`, `date_delete`, `real_return_visit`, `departure_moment`, `invalid_reason`, `visit_type`, `media_url`, `media_ext`, `media_duration`, `fail_reason`, `invited_shop`, `invite_situation`, `predict_arrive_shop_time`, `remind`, `predict_pick_up_time`, `fail_time`) " +
                "VALUES";
        try {
            // 保存sql后缀
            StringBuffer suffix = new StringBuffer();
            // 设置事务为非自动提交
            conn.setAutoCommit(false);
            // 比起st，pst会更好些
            //准备执行语句
            PreparedStatement pst = (PreparedStatement) conn.prepareStatement("");
            // 外层循环，总提交事务次数
            for (int i = 1; i <= 1000; i++) {
                suffix = new StringBuffer();
                // 第j次提交步长
                for (int j = 1; j <= 10000; j++) {
                    int x = j % 4;
                    String a = "";
                    if (x == 0) {
                        a = "'buyer_O2_level'";
                    } else {
                        a = "'buyer_O1_level'";
                    }
                    suffix.append(" (UUID(), UUID(), 'bZQDvC8M5T', '01134710', '客户被创建', 'wechat', " + a + ", NULL, '', NULL, 0, 1, '2019-02-27 16:46:04', '2019-02-27 16:45:42', NULL, NULL, NULL, '', 0, NULL, NULL, NULL, '资金预算不足', 0, 0, NULL, 0, NULL, NULL),");
                    // 构建SQL后缀
//                    suffix.append("('" + uutil.UUIDUtil.getUUID()+"','"+i*j+"','123456'"+ ",'男'"+",'教师'"+",'www.bbk.com'"+",'XX大学'"+",'"+"2016-08-12 14:43:26"+"','备注'" +"),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行SQL
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();
                // 提交事务
                conn.commit();
                System.out.println("提交" + i);
                // 清空上一次添加的数据
                suffix = new StringBuffer();
            }
            // 头等连接
            pst.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = System.currentTimeMillis();
        // 耗时
        System.out.println("1000万条数据插入花费时间 : " + (end - begin) / 1000 + " s");
        System.out.println("插入完成");
    }
}