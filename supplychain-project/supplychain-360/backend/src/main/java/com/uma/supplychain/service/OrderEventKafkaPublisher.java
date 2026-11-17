package com.uma.supplychain.service;

import com.uma.supplychain.events.OrderCreatedEvent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.event.TransactionPhase;

@Component
public class OrderEventKafkaPublisher {
    private final KafkaTemplate<String, OrderCreatedEvent> kafka;
    private final String topic;

    public OrderEventKafkaPublisher(KafkaTemplate<String, OrderCreatedEvent> kafka,
            @Value("${app.kafka.order-created-topic}") String topic) {
        this.kafka = kafka;
        this.topic = topic;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void publishAfterCommit(OrderCreatedEvent event) {
        kafka.send(topic, event.orderId().toString(), event);
    }
}
