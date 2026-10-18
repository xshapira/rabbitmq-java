package com.xshapira.rabbitmq_consumer.model;

import java.time.Instant;

public record Order(String id, String product, Instant date) {}
