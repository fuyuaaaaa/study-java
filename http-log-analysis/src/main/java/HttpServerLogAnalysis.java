import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author : fuyuaaa
 * @date : 2019-12-19 17:21
 */
@SuppressWarnings("all")
public class HttpServerLogAnalysis {

    public static void main(String[] args) throws Exception {
        File file = null;
        InputStreamReader inputReader;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入日志文件全路径名称");
            String fileName = scanner.nextLine();
            file = new File(fileName);
            try {
                inputReader = new InputStreamReader(new FileInputStream(file));
                System.out.println("文件加载成功");
                System.out.println("解析中...");
                break;
            } catch (FileNotFoundException e) {
                System.out.println("找不到文件OR加载文件出错");
            }
        }

        // 使用ArrayList来存储每行读取到的字符串
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            BufferedReader bf = new BufferedReader(inputReader);
            // 按行读取字符串
            String str;
            while ((str = bf.readLine()) != null) {
                if (StringUtils.isEmpty(str)) {
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
            log.set_path(split[11].replace("rsource:", ""));
            log.set_ext_params(split[13].replace("ext_params:", ""));
            try {
                String extParams = log.get_ext_params();
                JSONObject jsonObject = JSON.parseObject(extParams);
                String httpHeader = jsonObject.getString("httpHeader");
                JSONObject httpHeaderJson = JSON.parseObject(httpHeader);
                String userAgent = httpHeaderJson.getString("user-agent");
                log.setUserAgent(userAgent);
                String host = httpHeaderJson.getString("host");
                log.setHost(host);
                String string = httpHeaderJson.getString("x-forwarded-for");
                log.setRemoteIp(string);
            } catch (Exception e) {
            }
            logArrayList.add(log);
        }

        long botCount = logArrayList.stream().filter(httpServerLog -> isBot(httpServerLog.getUserAgent())).count();

        System.out.println("=====所有请求数:" + logArrayList.size());
        System.out.println("=====爬虫请求数:" + botCount);
        BigDecimal bigDecimal = new BigDecimal(botCount * 100).divide(new BigDecimal(logArrayList.size()), 2, RoundingMode.HALF_UP);
        System.out.println("=====爬虫请求比:" + bigDecimal.toPlainString() + "%");
        System.out.println("=====请求路径分析:\n" + JSONObject.toJSONString(analysisPath(logArrayList)));
        System.out.println("=====不重复ip数量" + unRepeetIpCount(logArrayList));

    }

    public static boolean isBot(String userAgent) {
        if (StringUtils.isEmpty(userAgent)) {
            return false;
        }
        userAgent = userAgent.toLowerCase();
        if (userAgent.contains("bot") || userAgent.contains("spider") || userAgent.contains("The Knowledge AI")) {
            return true;
        }
        return false;
    }

    private static List<Entry<String, Integer>> analysisPath(ArrayList<HttpServerLog> logArrayList) {
        Map<String, Integer> pathCountMap = new HashMap<>();
        logArrayList.forEach(httpServerLog -> {
            String path = httpServerLog.get_path();
            if (StringUtils.isEmpty(path)) {
                return;
            }
            if (path.contains("htm")) {
                path = path.split("htm")[0] + "htm";
            } else {
                path = path.split("%")[0];
            }

            if (pathCountMap.containsKey(path)) {
                pathCountMap.put(path, pathCountMap.get(path) + 1);
                return;
            }
            pathCountMap.put(path, 1);
        });

        List<Entry<String, Integer>> mapArrayList = new ArrayList<Entry<String, Integer>>(pathCountMap.entrySet());
        Collections.sort(mapArrayList, new Comparator<Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> map1, Map.Entry<String, Integer> map2) {
                return (map2.getValue() - map1.getValue());
            }
        });
        mapArrayList = mapArrayList.stream().filter(entry -> entry.getValue() > 5).collect(Collectors.toList());
        return mapArrayList;
    }

    public static Integer unRepeetIpCount(ArrayList<HttpServerLog> logArrayList) {
        Set<String> set = new HashSet<>(logArrayList.size());
        logArrayList.forEach(httpServerLog -> {
            String remoteIp = httpServerLog.getRemoteIp();
            if (StringUtils.isEmpty(remoteIp)) {
                return;
            }
            set.add(remoteIp);
        });
        return set.size();
    }

    @Data
    @SuppressWarnings("all")
    public static class HttpServerLog {

        String date;
        String _tid;
        String _path;
        String _ext_params;

        String userAgent;
        String host;
        String remoteIp;

    }

}