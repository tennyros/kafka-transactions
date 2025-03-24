package com.github.tennyros.transferservice.service.impl;

import com.github.tennyros.eventmodels.event.DepositRequestedEvent;
import com.github.tennyros.eventmodels.event.WithdrawalRequestedEvent;
import com.github.tennyros.transferservice.config.KafkaPropertiesConfig;
import com.github.tennyros.transferservice.error.TransferServiceException;
import com.github.tennyros.transferservice.model.TransferRestModel;
import com.github.tennyros.transferservice.service.TransferService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.HttpMethod.GET;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final KafkaPropertiesConfig kafkaProperties;
    private final RestTemplate restTemplate;

    @Override
    @Transactional
    public boolean transfer(TransferRestModel transferRestModel) {
        WithdrawalRequestedEvent withdrawalEvent = eventCreationWithdrawal(transferRestModel);
        DepositRequestedEvent depositEvent = eventCreationDeposit(transferRestModel);

        try {
            kafkaTemplate.send(kafkaProperties.getTopics().get("withdraw-money"), withdrawalEvent);
            log.info("Sent event to withdrawal topic: {}", withdrawalEvent);

            callRemoteService();

            kafkaTemplate.send(kafkaProperties.getTopics().get("deposit-money"), depositEvent);
            log.info("Sent event to deposit topic: {}", depositEvent);

        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new TransferServiceException(e);
        }

        return true;
    }

    private WithdrawalRequestedEvent eventCreationWithdrawal(TransferRestModel transferRestModel) {
        return WithdrawalRequestedEvent.builder()
                .senderId(transferRestModel.getSenderId())
                .recipientId(transferRestModel.getRecipientId())
                .amount(transferRestModel.getAmount())
                .build();
    }

    private DepositRequestedEvent eventCreationDeposit(TransferRestModel transferRestModel) {
        return DepositRequestedEvent.builder()
                .senderId(transferRestModel.getSenderId())
                .recipientId(transferRestModel.getRecipientId())
                .amount(transferRestModel.getAmount())
                .build();
    }

    private void callRemoteService() {

        String requestUrl = "http://localhost:8090/response/200";
        ResponseEntity<String> response = restTemplate.exchange(requestUrl, GET, null, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            log.info("Received response from mock service: {}", response.getBody());
        }

        if (response.getStatusCode().is5xxServerError()) {
            throw new TransferServiceException("Destination service not available");
        }
    }

}
