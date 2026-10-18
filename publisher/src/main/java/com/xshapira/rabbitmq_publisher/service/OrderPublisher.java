package com.xshapira.rabbitmq_publisher.service;

import static java.lang.IO.println;

import com.xshapira.rabbitmq_publisher.config.RabbitMqConfig;
import com.xshapira.rabbitmq_publisher.model.Order;
import com.xshapira.rabbitmq_publisher.model.OrderRequest;
import java.time.Instant;
import java.util.UUID;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderPublisher {
  private final RabbitTemplate rabbitTemplate;

  public OrderPublisher(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendOrder(OrderRequest orderRequest) {
    var order = new Order(UUID.randomUUID().toString(), orderRequest.product(), Instant.now());
    var routingKey =
        "premium".equalsIgnoreCase(orderRequest.orderType())
            ? RabbitMqConfig.PREM_ROUTING_KEY
            : RabbitMqConfig.STD_ROUTING_KEY;
    rabbitTemplate.convertAndSend(RabbitMqConfig.EXCHANGE, routingKey, order);
    println("Sending order: " + order);
  }
}
