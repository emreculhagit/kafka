
package com.kafka.payment_service.service;

import com.kafka.payment_service.event.PaymentResultEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.kafka.payment_service.event.PaymentFailedEvent;

@Service
public class PaymentProducerService {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public PaymentProducerService(
            KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendPaymentResult(PaymentResultEvent event) {
        kafkaTemplate.send("payment-result", event);
    }
    public void sendPaymentFailed(PaymentFailedEvent event) {
        kafkaTemplate.send("payment-failed", event);
    }
}
