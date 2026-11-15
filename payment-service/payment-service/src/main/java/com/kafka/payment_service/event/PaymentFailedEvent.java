
package com.kafka.payment_service.event;

public record PaymentFailedEvent(
        Long orderId,
        String message
) {}
