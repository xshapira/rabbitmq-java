package com.xshapira.rabbitmq_publisher.controller;

import com.xshapira.rabbitmq_publisher.model.OrderRequest;
import com.xshapira.rabbitmq_publisher.service.OrderPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
  private final OrderPublisher orderPublisher;

  public OrderController(OrderPublisher orderPublisher) {
    this.orderPublisher = orderPublisher;
  }

  @PostMapping
  public ResponseEntity<String> createOrder(@RequestBody OrderRequest orderRequest) {
    orderPublisher.sendOrder(orderRequest);
    return ResponseEntity.ok("Order successfully placed.");
  }
}
