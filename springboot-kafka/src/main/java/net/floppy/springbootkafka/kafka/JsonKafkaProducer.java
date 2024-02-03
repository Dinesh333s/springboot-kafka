package net.floppy.springbootkafka.kafka;

import net.floppy.springbootkafka.payload.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
public class JsonKafkaProducer {
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonKafkaProducer.class);
    private KafkaTemplate<String, User> template;

    @Value("${spring.kafka.Json.topic.name}")
    public String topicName;

    public JsonKafkaProducer(KafkaTemplate<String, User> template) {
        this.template = template;
    }

    public void sendMessage(User data)
    {
        LOGGER.info(String.format("Message sent --> %s", data.toString()));

        Message<User> message = MessageBuilder.withPayload(data).setHeader(KafkaHeaders.TOPIC, topicName).build();

        template.send(message);

    }
}
