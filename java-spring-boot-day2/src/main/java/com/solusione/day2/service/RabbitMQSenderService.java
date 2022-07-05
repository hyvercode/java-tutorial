package com.solusione.day2.service;

import com.solusione.day2.model.request.UserRequest;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
@Service
public class RabbitMQSenderService {

    private final AmqpTemplate rabbitTemplate;

    public RabbitMQSenderService(AmqpTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Value("${spring.rabbitmq.exchange}")
    private String exchange;

    @Value("${spring.rabbitmq.routingkey}")
    private String routingkey;

    public void send(UserRequest userRequest) {
        rabbitTemplate.convertAndSend(exchange, routingkey, userRequest);
        System.out.println("Send msg = " + userRequest);
    }
}
