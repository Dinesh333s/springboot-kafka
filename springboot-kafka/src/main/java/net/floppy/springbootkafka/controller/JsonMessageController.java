package net.floppy.springbootkafka.controller;

import net.floppy.springbootkafka.kafka.JsonKafkaProducer;
import net.floppy.springbootkafka.payload.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/kafka/")
public class JsonMessageController {
    private JsonKafkaProducer jsonKafkaProducer;

    public JsonMessageController(JsonKafkaProducer jsonKafkaProducer) {
        this.jsonKafkaProducer = jsonKafkaProducer;
    }

    @PostMapping("publish")
    public ResponseEntity<String> sendMessage(@RequestBody User user)
    {
        jsonKafkaProducer.sendMessage(user);
        return ResponseEntity.ok("Json Message sent");
    }
}
