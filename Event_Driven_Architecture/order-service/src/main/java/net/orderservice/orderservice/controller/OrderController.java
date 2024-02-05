package net.orderservice.orderservice.controller;

import net.basedomains.basedomains.dto.Order;
import net.basedomains.basedomains.dto.OrderEvent;
import net.orderservice.orderservice.kafka.OrderProducer;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("api/v1")
public class OrderController {
    private OrderProducer orderProducer;

    public OrderController(OrderProducer orderProducer) {
        this.orderProducer = orderProducer;
    }

    @PostMapping("/orders")
    public String placeOrder(@RequestBody Order order)
    {
        order.setOrderId(UUID.randomUUID().toString()); //This will set unique random id as orderId
        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrder(order);
        orderEvent.setStatus("PENDING");
        orderEvent.setMessage("Order status is in pending state");

        orderProducer.sendMessage(orderEvent);

      return "Ordered Successfully...";
    }

}
