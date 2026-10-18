package com.xshapira.rabbitmq_consumer.service;

import static java.lang.IO.println;

import com.xshapira.rabbitmq_consumer.config.RabbitMqConfig;
import com.xshapira.rabbitmq_consumer.model.Order;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

  @RabbitListener(queues = RabbitMqConfig.STD_QUEUE)
  public void processStandardOrder(Order order) {
    println("Processing standard order: " + order);
  }

  @RabbitListener(queues = RabbitMqConfig.PREM_QUEUE)
  public void processPremiumOrder(Order order) {
    println("Processing premium order: " + order);
  }
}
