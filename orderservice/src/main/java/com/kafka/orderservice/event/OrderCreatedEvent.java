package com.kafka.orderservice.event;

public record OrderCreatedEvent(
        Long orderId,
        Long customerId,
        Long productId,
        Integer quantity,
        Double amount
) {
}