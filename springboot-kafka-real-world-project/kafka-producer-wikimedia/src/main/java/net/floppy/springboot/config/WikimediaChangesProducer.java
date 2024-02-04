package net.floppy.springboot.config;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;
import org.apache.kafka.clients.consumer.internals.events.BackgroundEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private final KafkaTemplate<String,String> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topic;
    @Value("${EVENT_URL}")
    private String event_url;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage()
    {
//       to read realtime stream media from wikimedia, we use event source
        BackgroundEventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate,topic);
        BackgroundEventSource backgroundEventSource = new BackgroundEventSource.Builder(eventHandler,
                new EventSource.Builder(URI.create(event_url))).build();
          backgroundEventSource.start();
        try {
            TimeUnit.MINUTES.sleep(10);
        } catch (InterruptedException e) {
            LOGGER.error(String.format("Error: %e", e.getMessage()));
            throw new RuntimeException("An error has occurred");
        }

    }
}