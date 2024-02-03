package net.floppy.springbootkafka.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    @Value("$spring.kafka.Json.topic.name")
    public String topicName;

    @Value("$spring.kafka.topic.name")
    public String topic;
    @Bean
    public NewTopic floppyTopicKafka() {
        return TopicBuilder.name(topic).build();
    }

    @Bean
    public NewTopic floppyJsonTopicKafka() {
        return TopicBuilder.name(topicName).build();
    }
}
