package top.fuyuaaa.study.netty.im.oio;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @description : 传统IO 
 * @author : fuyuaaa
 * @create : 2019-07-09 10:43
 */
@SuppressWarnings("all")
public class IOClient {

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}