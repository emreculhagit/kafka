
package com.kafka.payment_service.event;

public record PaymentResultEvent(
        Long orderId,
        boolean success,
        String message
) {
}
