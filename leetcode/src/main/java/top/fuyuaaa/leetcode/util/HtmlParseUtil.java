package top.fuyuaaa.leetcode.util;

import com.alibaba.fastjson.JSON;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;
import top.fuyuaaa.leetcode.dao.FinishSolutionPo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : fuyuaaa
 * @date : 2020-08-26 10:06
 */
public class HtmlParseUtil {

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
        List<FinishSolutionPo> finishSolutionPoList = HtmlParseUtil.getFinish();
        finishSolutionPoList.forEach(finishSolutionPo -> {
            String url = "http://106.14.169.161:9090/finish/solution/add?solutionId={solutionId}&solutionName={solutionName}&solutionUrl={solutionUrl}";
            Map<String, Object> params = new HashMap<>();
            params.put("solutionId", finishSolutionPo.getSolutionId());
            params.put("solutionName", finishSolutionPo.getSolutionName());
            params.put("solutionUrl", "https://leetcode-cn.com/" + finishSolutionPo.getSolutionUrl());
            String msg = restTemplate.getForObject(url, String.class, params);
            Assert.state("插入成功".equals(msg), "失败");
        });

    }

    public static List<FinishSolutionPo> getFinish() {
        try {
            String src = "/Users/fuyuaaa/work/IdeaProjects/study-java/leetcode/src/main/resources/templates/x.html";
            File file = new File(src);
            FileReader fileReader = new FileReader(file);
            BufferedReader br = new BufferedReader(fileReader);
            StringBuilder sb = new StringBuilder();
            String temp;
            while ((temp = br.readLine()) != null) {
                // 拼接换行符
                sb.append(temp).append("\n");
            }
            br.close();
            String html = sb.toString();

            Document document = Jsoup.parse(html);
            Elements rows = document.select("table[class=table table-striped]").get(0).select("tr");

            List<FinishSolutionPo> finishSolutionPoList = new ArrayList<>(rows.size() - 2);
            for (int i = 1; i < rows.size() - 1; i++) {
                Element row = rows.get(i);

                FinishSolutionPo finishSolutionPo = new FinishSolutionPo();
                finishSolutionPo.setSolutionId(row.select("td").get(1).text());
                finishSolutionPo.setSolutionName(row.select("td").get(2).text());
                finishSolutionPo.setSolutionUrl(row.select("td").get(2).select("a").attr("href"));
                finishSolutionPoList.add(finishSolutionPo);

                System.out.println("-----------------------------------------------------------------");
                System.out.print("题目ID:" + row.select("td").get(1).text());
                System.out.print("题目NAME:" + row.select("td").get(2).text());
                System.out.print("题目URL:" + row.select("td").get(2).select("a").attr("href"));
                System.out.println(i + "-----------------------------------------------------------------");
            }
            System.out.println(JSON.toJSONString(finishSolutionPoList));
            return finishSolutionPoList;

        } catch (Exception e) {
            return null;
        }

    }
}
