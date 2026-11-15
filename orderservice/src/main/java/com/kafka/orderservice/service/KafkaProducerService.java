package com.kafka.orderservice.service;

import com.kafka.orderservice.event.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public KafkaProducerService(
            KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendOrder(OrderCreatedEvent event) {
        kafkaTemplate.send("order-created", event);
    }
}