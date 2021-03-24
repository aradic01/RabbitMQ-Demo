package com.radicant.rabbitmq.demo.consumer;

import com.radicant.rabbitmq.demo.config.constant.MessagingConst;
import com.radicant.rabbitmq.demo.dto.OrderStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class User {

    @RabbitListener(queues = MessagingConst.QUEUE)
    public void consumeMessage(OrderStatus orderStatus) {
        System.out.println("Message received: " + orderStatus);
    }
}
