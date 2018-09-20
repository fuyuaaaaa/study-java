package fuyuaaa.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author: fuyuaaaaa
 * @description:
 * @program: study
 * @creat: 2018-09-08 16:53
 **/
public class UrlTest {
    public static void main(String[] args) {
        try {
            URL fuyu = new URL("http://www.fuyuaaa.top");
            URL url = new URL(fuyu, "/login");
            System.out.println("协议：" + url.getProtocol());//协议
            System.out.println("主机：" + url.getHost());
            System.out.println("端口：" + url.getPort());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        try {
            //create url
            URL url = new URL("http://www.baidu.com");
            //获取URL对象所表示的资源的字节输入流
            InputStream is = url.openStream();
            //字节输入流>>==字符输入流
            InputStreamReader isr = new InputStreamReader(is,"utf-8");
            //为字符输入流添加缓冲
            BufferedReader br = new BufferedReader(isr);
            String data = br.readLine();
            while (data != null) {
                System.out.println(data);
                data=br.readLine();
            }
            br.close();
            isr.close();
            is.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
