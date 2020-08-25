import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.utils.Time;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.Timer;

/**
 * @author : fuyuaaa
 * @date : 2020-08-07 16:20
 */
public class ConsumerTest {

    public static void main(String[] args) {
        String topicName = "my-topic";
        String groupID = "test-group";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", groupID);
        props.put("enable.auto.comm", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("auto.offset.reset", "earliest");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList(topicName));
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
                for (ConsumerRecord<String, String> record : records)
                    System.out.printf("offset ＝ %d, key ＝ %s, value ＝%s%n", record.offset(), record.key(), record.value());
            }
        } finally {
            consumer.close();
        }
    }

}