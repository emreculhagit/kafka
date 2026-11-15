
package com.kafka.orderservice.consumer;

import com.kafka.orderservice.service.OrderStatusService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentFailedConsumer {

    private final OrderStatusService orderStatusService;

    public PaymentFailedConsumer(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @KafkaListener(
            topics = "payment-failed",
            groupId = "order-group"
    )
    public void consume(Map<String, Object> event) {

        Long orderId = ((Number) event.get("orderId")).longValue();

        orderStatusService.updateStatus(orderId, "CANCELLED");

        System.out.println("Ödeme başarısız!");
        System.out.println("Order ID: " + orderId);
        System.out.println("Sipariş CANCELLED olarak güncellendi.");
    }
}
