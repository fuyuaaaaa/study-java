package top.fuyuaaa.study.mq.helloworld;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-06 18:00
 */
public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        send("1");
    }

    public static void send(String msg) throws IOException, TimeoutException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("106.14.169.161");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "hello world! I am " + msg;
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" fuyuaaa sent message: " + message);
    }
}
