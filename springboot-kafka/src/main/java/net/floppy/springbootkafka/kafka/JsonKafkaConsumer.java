package net.floppy.springbootkafka.kafka;

import net.floppy.springbootkafka.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaConsumer {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
    @KafkaListener(topics = "${spring.kafka.Json.topic.name}" , groupId = "${spring.kafka.consumer.group-id}")
    public void JsonConsumer(User data)
    {
        LOGGER.info(String.format("Message received --> %s",data.toString()));
    }
}
