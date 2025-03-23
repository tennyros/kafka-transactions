package com.github.tennyros.depositservice.handle;

import com.github.tennyros.eventmodels.event.DepositRequestedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "deposit-money-topic", containerFactory = "kafkaListenerContainerFactory")
public class DepositRequestedEventHandler {

    @KafkaHandler
    public void handle(@Payload DepositRequestedEvent event) {
        log.info("Received deposit event amount: {}", event.getAmount());
    }
}
