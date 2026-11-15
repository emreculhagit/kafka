package com.kafka.orderservice.consumer;

import com.kafka.orderservice.service.OrderStatusService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class PaymentResultConsumer {

    private final OrderStatusService orderStatusService;

    public PaymentResultConsumer(OrderStatusService orderStatusService) {
        this.orderStatusService = orderStatusService;
    }

    @KafkaListener(
            topics = "payment-result",
            groupId = "order-group"
    )
    public void consume(Map<String, Object> result) {

        Long orderId = ((Number) result.get("orderId")).longValue();
        boolean success = (Boolean) result.get("success");

        String status = success ? "APPROVED" : "CANCELLED";

        orderStatusService.updateStatus(orderId, status);

        System.out.println("Order ID: " + orderId);
        System.out.println("Yeni sipariş durumu: " + status);
    }
}