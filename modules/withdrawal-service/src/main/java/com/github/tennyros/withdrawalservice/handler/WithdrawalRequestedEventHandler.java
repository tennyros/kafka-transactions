package com.github.tennyros.withdrawalservice.handler;

import com.github.tennyros.eventmodels.event.WithdrawalRequestedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@KafkaListener(topics = "withdraw-money-topic", containerFactory = "kafkaListenerContainerFactory")
public class WithdrawalRequestedEventHandler {

    @KafkaHandler
    public void handle(@Payload WithdrawalRequestedEvent event) {
        log.info("Received withdrawal event amount: {}", event.getAmount());
    }
}
