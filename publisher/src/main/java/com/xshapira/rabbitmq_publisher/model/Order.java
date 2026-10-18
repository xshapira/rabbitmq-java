package com.xshapira.rabbitmq_publisher.model;

// import java.util.Date;
import java.time.Instant;

public record Order(String id, String product, Instant date) {}
