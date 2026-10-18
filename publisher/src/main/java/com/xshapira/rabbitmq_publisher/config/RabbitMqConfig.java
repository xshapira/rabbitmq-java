package com.xshapira.rabbitmq_publisher.config;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.JacksonJsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {
  public static final String STD_QUEUE = "standard-order-queue";
  public static final String PREM_QUEUE = "premium-order-queue";
  public static final String EXCHANGE = "order-exchange";
  public static final String STD_ROUTING_KEY = "standard-order";
  public static final String PREM_ROUTING_KEY = "premium-order";

  @Bean
  public Queue standardOrderQueue() {
    return new Queue(STD_QUEUE);
  }

  @Bean
  public Queue premiumOrderQueue() {
    return new Queue(PREM_QUEUE);
  }

  @Bean
  public DirectExchange exchange() {
    return new DirectExchange(EXCHANGE);
  }

  @Bean
  public Binding stdBinding(@Qualifier("standardOrderQueue") Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(STD_ROUTING_KEY);
  }

  @Bean
  public Binding premBinding(@Qualifier("premiumOrderQueue") Queue queue, DirectExchange exchange) {
    return BindingBuilder.bind(queue).to(exchange).with(PREM_ROUTING_KEY);
  }

  @Bean
  public MessageConverter messageConverter() {
    return new JacksonJsonMessageConverter();
  }

  @Bean
  public AmqpTemplate template(ConnectionFactory connectionFactory) {
    RabbitTemplate template = new RabbitTemplate(connectionFactory);
    template.setMessageConverter(messageConverter());
    return template;
  }
}
