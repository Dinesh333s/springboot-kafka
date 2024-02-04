package net.floppy.springboot.config;

import com.launchdarkly.eventsource.MessageEvent;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import okhttp3.internal.http2.Http2Reader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class WikimediaChangesHandler implements BackgroundEventHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesHandler.class);
    private KafkaTemplate<String,String> kafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topic;

    public WikimediaChangesHandler(KafkaTemplate<String, String> kafkaTemplate, String topic){
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;

    }

    @Override
    public void onOpen() throws Exception {

    }

    @Override
    public void onClosed() throws Exception {

    }

    @Override
    public void onMessage(String s, MessageEvent messageEvent) throws Exception {
       LOGGER.info(String.format("Event data --> %s",messageEvent.getData()));
       kafkaTemplate.send(topic,messageEvent.getData());
    }

    @Override
    public void onComment(String s) throws Exception {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
