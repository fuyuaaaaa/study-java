import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.Cluster;
import org.apache.kafka.common.PartitionInfo;

import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * @author : fuyuaaa
 * @date : 2020-08-06 09:39
 */
public class ProducerTest {
    public static void main(String[] args) throws Exception {
        Properties props = new Properties();

        //bootstrap.servers,key.serializer,value.serializer必须指定
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("acks", "-1");
        props.put("retries", 3);
        props.put("batch.size", 323840);
        props.put("linger.ms", 10);
        props.put("buffer.memory", 33554432);
        props.put("max.block.ms", 3000);
        Producer<String, String> producer = new KafkaProducer<>(props);
        for (int i = 0; i < 10; i++) {
            ProducerRecord<String, String> record = new ProducerRecord<>("my-topic", Integer.toString(i), Integer.toString(i));
            int x = i;
            producer.send(record, new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (null == exception) {
                        System.out.println("发送成功: " + x);
                    }
                }
            });
        }
        producer.close();
    }
}
