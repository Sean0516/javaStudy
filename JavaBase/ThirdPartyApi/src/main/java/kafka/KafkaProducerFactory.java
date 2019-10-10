package kafka;

import apache.httpclient.HttpClientUtil;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.checkerframework.checker.units.qual.K;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sean
 * @date 2019/9/16  16:14
 */
public class KafkaProducerFactory {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerFactory.class);
    private KafkaProducer<String, String> kafkaProducer = null;

    public void initProducer(String bootStrapServers) {
        LOGGER.info("start init kafka producer  bootstrap server is {}", bootStrapServers);
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootStrapServers);
        props.put(ProducerConfig.BATCH_SIZE_CONFIG, 65536);
        props.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 524288);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        kafkaProducer = new KafkaProducer<>(props);
    }

    public void sendMsg(String topic, String msg) {
        if (kafkaProducer != null) {
            LOGGER.debug("start snd msg to kafka server and topic is {} ,msg is {}", topic, msg);
            ProducerRecord<String, String> record = new ProducerRecord<>(topic, msg);
            kafkaProducer.send(record);
        } else {
            throw new RuntimeException("kafka producer may not init ,please check it ");
        }
    }

    public void close() {
        if (kafkaProducer != null) {
            kafkaProducer.close();
        }
    }

    public static void main(String[] args) {
        KafkaProducerFactory kafkaProducerFactory=new KafkaProducerFactory();
        kafkaProducerFactory.initProducer("192.168.1.2:9812");

    }

}
