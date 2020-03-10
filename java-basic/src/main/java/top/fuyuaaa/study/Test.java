package top.fuyuaaa.study;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Iterables;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.util.internal.StringUtil;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import lombok.Data;
import org.apache.curator.shaded.com.google.common.base.Stopwatch;

/**
 *
 * @author : fuyuaaa
 * @date : 2019-12-19 13:59
 */
public class Test {


    private static String url = "jdbc:mysql://106.14.169.161:3306/test?useSSL=false&rewriteBatchedStatements=true&useUnicode=true&amp&characterEncoding=UTF-8";
    private static String user = "root";
    private static String password = "Fuyu.742423672";
    private static ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("thread-%d").build();
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
        4 * 2,
        10,
        0, TimeUnit.SECONDS,
        new LinkedBlockingDeque<>(64),
        nameThreadFactory,
        new ThreadPoolExecutor.AbortPolicy()
    );

    public static void main(String[] args) throws Exception{
        Stopwatch stopwatch = Stopwatch.createStarted();
        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            File file = new File("/Users/fuyuaaa/Desktop/http-server_2019-12-18.log.10");
            InputStreamReader inputReader = new InputStreamReader(new FileInputStream(file));
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                if (StringUtil.isNullOrEmpty(str)) {
                    continue;
                }
                arrayList.add(str);
            }
            bf.close();
            inputReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<HttpServerLog> logArrayList = new ArrayList<>(arrayList.size());
        for (String str : arrayList) {
            str = str.replace(" _tid", "\t_tid");
            String[] split = str.split("\t_");
            HttpServerLog log = new HttpServerLog();
            log.setDate(split[0].substring(0, 23));
            log.set_tid(split[1].replace("tid:", ""));
            log.set_path(split[19].replace("path:", ""));
            log.set_ext_params(split[13].replace("ext_params:", ""));

            try {
                String extParams = log.get_ext_params();
                JSONObject jsonObject = JSON.parseObject(extParams);
                String httpHeader = jsonObject.getString("httpHeader");
                JSONObject httpHeaderJson = JSON.parseObject(httpHeader);
                String userAgent = httpHeaderJson.getString("user-agent");
                log.setUserAgent(userAgent);
            } catch (Exception e) {
            }
            logArrayList.add(log);

        }

        Iterable<List<HttpServerLog>> parts = Iterables.partition(logArrayList, 5000);
        CountDownLatch latch = new CountDownLatch(logArrayList.size() / 5000 + 1);
        parts.forEach(part -> threadPool.execute(() -> {
            executeBatch(part);
            latch.countDown();
        }));
        latch.await();
        stopwatch.stop();
        System.out.println("====耗时:"+stopwatch.elapsed(TimeUnit.MILLISECONDS)+"ms===");
    }


    private static void executeBatch(List<HttpServerLog> logArrayList) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            String sql = "insert into tbl_log(date,_tid,_path,_ext_params,user_agent) values(?,?,?,?,?)";
            conn.setAutoCommit(false);
            ps = conn.prepareStatement(sql);
            logArrayList.forEach(log -> {

            });
            for (HttpServerLog log : logArrayList) {
                ps.setString(1, log.getDate());
                ps.setString(2, log.get_tid());
                ps.setString(3, log.get_path());
                ps.setString(4, log.get_ext_params());
                ps.setString(5, log.getUserAgent());
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


    @Data
    @SuppressWarnings("all")
    public static class HttpServerLog {

        String date;
        String _tid;
        String _path;
        String userAgent;
        String _ext_params;
    }
}
