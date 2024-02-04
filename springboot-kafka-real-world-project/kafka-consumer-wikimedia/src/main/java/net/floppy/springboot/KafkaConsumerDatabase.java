package net.floppy.springboot;

import net.floppy.springboot.Entity.Wikimedia;
import net.floppy.springboot.Repository.WikimediaRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerDatabase {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumerDatabase.class);
    private WikimediaRepo wikimediaRepo;
    public KafkaConsumerDatabase(WikimediaRepo wikimediaRepo) {
        this.wikimediaRepo = wikimediaRepo;
    }



    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumer(String message) {
        LOGGER.info(String.format("Message Received --> %s", message));
        Wikimedia wikimedia = new Wikimedia();
        wikimedia.setEvent(message);
        wikimediaRepo.save(wikimedia);
    }
}
