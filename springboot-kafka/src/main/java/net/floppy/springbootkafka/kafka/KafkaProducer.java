package net.floppy.springbootkafka.kafka;

import org.apache.naming.java.javaURLContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducer {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducer.class);
    private KafkaTemplate<String,String> template;

    @Value("${spring.kafka.topic.name}")
    public String topicName;

    public KafkaProducer(KafkaTemplate<String, String> template) {
        this.template = template;
    }
    public void sendMessage(String message)
    {
        LOGGER.info(String.format("Message sent %s",message));
        template.send(topicName,message);
    }
}
