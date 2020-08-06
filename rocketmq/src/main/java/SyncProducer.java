
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @author : fuyuaaa
 * @date : 2020-07-19 22:11
 */
public class SyncProducer {
    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("producer_test");
        producer.setNamesrvAddr("192.168.1.18:9876");
        producer.start();
        for (int i = 0; i < 100; i++) {
            Message message = new Message("topic_test", "tag_test",
                    ("hello rocketMq " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            SendResult sendResult = producer.send(message);
            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}
