package top.fuyuaaa.leetcode.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author : fuyuaaa
 * @date : 2020-08-26 09:15
 */
public class EmailSendUtil {

    private final static String USER = "daVinci";
    private final static String PASS = "BaMrObqzdozbfQPz8QnAEb4d7THX51G7";
    private final static String SERVER = "http://msgcenter.prepub.souche.com";
    private final static String SEND_PATH = "/v1/mail";
    private final static Integer PROCESSORS = Runtime.getRuntime().availableProcessors();


    public static void sendEmail(String to, String subject, String html) {
        ThreadFactory nameThreadFactory = new ThreadFactoryBuilder().setNameFormat("email-thread-%d").build();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                PROCESSORS * 2,
                PROCESSORS * 4,
                60, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(1024),
                nameThreadFactory,
                new ThreadPoolExecutor.AbortPolicy()
        );
        threadPoolExecutor.submit(() -> {
            String auth = USER + ":" + PASS;
            String encodeAuth = Base64.getEncoder().encodeToString(auth.getBytes());
            Map<String, Object> headers = new HashMap<>(1);
            headers.put("Authorization", "Basic " + encodeAuth);

            Map<String, Object> params = new HashMap<>();
            params.put("to", to);
            params.put("subject", subject);
            params.put("html", html);
            try {
                OkHttpUtil.postForm(SERVER + SEND_PATH, params, headers);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}