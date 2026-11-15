package com.kafka.payment_service.event;

public record OrderCreatedEvent(
        Long orderId,
        Long customerId,
        Long productId,
        Integer quantity,
        Double amount
) {
}
