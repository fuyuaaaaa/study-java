package top.fuyuaaa.study.mq.workqueue;

import java.io.IOException;
import java.util.concurrent.TimeoutException;
import top.fuyuaaa.study.mq.helloworld.Send;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 18:00
 */
public class NewTask {

    public static void main(String[] args) throws IOException, TimeoutException {
        for (int i = 0; i < 10; i++) {
            Send.send(i + ".");
        }
    }
}
