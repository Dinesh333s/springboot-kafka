package net.stockservice.stockservice.kafka;

import net.basedomains.basedomains.dto.OrderEvent;
import org.apache.kafka.common.TopicCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);


    @KafkaListener(topics = "${spring.kafka.topic.name}",groupId = "${spring.kafka.consumer.group-id}")
    public void orderConsumer(OrderEvent orderEvent)
    {
        LOGGER.info(String.format("Order event Received in stock service => %s",orderEvent.toString()));

        //write code to save into the db
    }
}
