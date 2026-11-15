
package com.kafka.payment_service.consumer;

import com.kafka.payment_service.event.OrderCreatedEvent;
import com.kafka.payment_service.event.PaymentFailedEvent;
import com.kafka.payment_service.event.PaymentResultEvent;
import com.kafka.payment_service.service.PaymentProducerService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class PaymentConsumer {

    private final PaymentProducerService paymentProducerService;

    public PaymentConsumer(PaymentProducerService paymentProducerService) {
        this.paymentProducerService = paymentProducerService;
    }

    @KafkaListener(
            topics = "order-created",
            groupId = "payment-group"
    )
    public void consume(OrderCreatedEvent event) {

        System.out.println("Sipariş alındı: " + event.orderId());

        boolean success = event.amount() <= 1000;

        if (success) {

            PaymentResultEvent result = new PaymentResultEvent(
                    event.orderId(),
                    true,
                    "Ödeme başarılı"
            );

            paymentProducerService.sendPaymentResult(result);

            System.out.println("Ödeme başarılı event'i gönderildi.");

        } else {

            PaymentFailedEvent failedEvent = new PaymentFailedEvent(
                    event.orderId(),
                    "Ödeme başarısız"
            );

            paymentProducerService.sendPaymentFailed(failedEvent);

            System.out.println("Ödeme başarısız event'i gönderildi.");
        }
    }

}
