package top.fuyuaaa.study.mq.topic;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @description :  
 * @author : fuyuaaa
 * @create : 2019-08-07 16:49
 */
@SuppressWarnings("all")
public class TopicLogProducer {

    private static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("106.14.169.161");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()) {

            channel.exchangeDeclare(EXCHANGE_NAME, "topic");
            String severity = "kern.nihao";
            String message = "nihao Message";
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

            severity = "hello";
            message = "hello Message";
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
            System.out.println(" [x] Sent '" + severity + "':'" + message + "'");

        }
    }
}
