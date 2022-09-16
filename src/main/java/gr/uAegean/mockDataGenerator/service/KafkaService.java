package gr.uAegean.mockDataGenerator.service;

import gr.uAegean.mockDataGenerator.model.LegacySystem;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaService {
    private final KafkaProducer<String, LegacySystem> legacySystemKafkaProducer;

    @Autowired
    public KafkaService(KafkaProducer<String, LegacySystem> legacySystemKafkaProducer) {
        this.legacySystemKafkaProducer = legacySystemKafkaProducer;
    }

    public void saveToKafkaGeneric(String topic, Object kafkaObject) {
        try {
            log.info("pushing to kafka {}", kafkaObject);
            this.legacySystemKafkaProducer.send(new ProducerRecord(topic, kafkaObject));
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
//            kafkaObject.close();
        }
    }

}
