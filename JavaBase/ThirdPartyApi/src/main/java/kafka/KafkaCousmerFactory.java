package kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * @author sean
 * @date 2019/9/16  16:57
 */
public class KafkaCousmerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCousmerFactory.class);

    public KafkaConsumer<String, String>  initConsumer(String bootStrapServers, String groupId, boolean flag ,String... topic) {

        LOGGER.info("start init consumer servers is {} ,group id is {} ,topic is {}", bootStrapServers, groupId, topic);
        Map<String, Object> propsMap = new HashMap<>();
        propsMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        propsMap.put(ConsumerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, 500);
        propsMap.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 2000);
        propsMap.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, flag);
        propsMap.put(ConsumerConfig.SESSION_TIMEOUT_MS_CONFIG, 60000);
        propsMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        propsMap.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        KafkaConsumer<String, String>  kafkaConsumer = new KafkaConsumer<>(propsMap);
        List<String> stringList = Arrays.asList(topic);
        kafkaConsumer.subscribe(stringList);
        return kafkaConsumer;
    }

//    public ConsumerRecords<String, String> pollMsg() {
//        ConsumerRecords<String, String> poll = null;
//        if (kafkaConsumer != null) {
//            poll = kafkaConsumer.poll(100);
//            kafkaConsumer.commitSync();
//        } else {
//            throw new RuntimeException("kafka consumer  may not init ,please check it ");
//        }
//        return poll;
//    }

    public static void main(String[] args) {
        KafkaCousmerFactory kafkaCousmerFactory=new KafkaCousmerFactory();
        KafkaConsumer<String, String> kafkaConsumer = kafkaCousmerFactory.initConsumer("192.168.4.104:9092", "test", true,"voice-smdr");

        while (true){
            ConsumerRecords<String, String> poll = kafkaConsumer.poll(10);
            for (ConsumerRecord<String, String> stringStringConsumerRecord : poll) {
                System.out.println(stringStringConsumerRecord.value());
            }

        }
    }
}
