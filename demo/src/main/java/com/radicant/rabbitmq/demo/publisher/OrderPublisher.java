package com.radicant.rabbitmq.demo.publisher;

import com.radicant.rabbitmq.demo.config.constant.MessagingConst;
import com.radicant.rabbitmq.demo.dto.Order;
import com.radicant.rabbitmq.demo.dto.OrderStatus;
import com.radicant.rabbitmq.demo.dto.enums.StatusType;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/order")
public class OrderPublisher {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/{hotelName}")
    public ResponseEntity<?> bookOrder(@RequestBody Order order, @PathVariable String hotelName) {

        //set OrderId dynamically
        order.setOrderId(UUID.randomUUID().toString());

        OrderStatus orderStatus = new OrderStatus(
                order, StatusType.IN_PROGRESS,
                "Order for the " + hotelName + " hotel placed successfully!"
        );
        rabbitTemplate.convertAndSend(MessagingConst.EXCHANGE, MessagingConst.ROUTING_KEY, orderStatus);

        return new ResponseEntity<>(orderStatus, HttpStatus.CREATED);
    }
}
