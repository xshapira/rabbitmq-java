package com.xshapira.rabbitmq_consumer.config;

import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  public static final String STD_QUEUE = "standard-order-queue";
  public static final String PREM_QUEUE = "premium-order-queue";

  @Bean
  public MessageConverter messageConverter() {
    return new JacksonJsonMessageConverter();
  }
}
