
package com.kafka.payment_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.util.backoff.FixedBackOff;

@Configuration
public class KafkaConfig {


    @Bean
    public DefaultErrorHandler errorHandler() {
        DefaultErrorHandler handler = new DefaultErrorHandler(
                (record, exception) -> {
                    System.out.println("Retry denemeleri tamamlandı. Mesaj: "
                            + record.value());
                },
                new FixedBackOff(1000L, 2L)
        );

        handler.setRetryListeners((record, ex, deliveryAttempt) -> {
            System.out.println("Retry denemesi: " + deliveryAttempt);
        });

        return handler;
    }

}
