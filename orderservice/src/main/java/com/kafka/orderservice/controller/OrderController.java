package com.kafka.orderservice.controller;

import com.kafka.orderservice.event.OrderCreatedEvent;
import com.kafka.orderservice.service.KafkaProducerService;
import com.kafka.orderservice.service.OrderStatusService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final KafkaProducerService kafkaProducerService;

    private final AtomicLong orderIdGenerator = new AtomicLong(0);
    private final OrderStatusService orderStatusService;


    public OrderController(KafkaProducerService kafkaProducerService,OrderStatusService orderStatusService) {
        this.kafkaProducerService = kafkaProducerService;
        this.orderStatusService = orderStatusService;
    }

    @PostMapping
    public String createOrder(@RequestBody OrderCreatedEvent request) {

        OrderCreatedEvent event = new OrderCreatedEvent(
                orderIdGenerator.incrementAndGet(),
                request.customerId(),
                request.productId(),
                request.quantity(),
                request.amount()
        );
        orderStatusService.addOrder(event.orderId());
        kafkaProducerService.sendOrder(event);

        return "Sipariş Kafka'ya gönderildi. Order ID: " + event.orderId();
    }

    @GetMapping("/{orderId}")
    public String getOrderStatus(@PathVariable Long orderId) {

        String status = orderStatusService.getStatus(orderId);

        if (status == null) {
            return "Sipariş bulunamadı.";
        }

        return "Order ID: " + orderId + " | Status: " + status;
    }
}