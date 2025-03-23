package com.github.tennyros.transferservice.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class TransferRestModel {

    private String senderId;
    private String recipientId;
    private BigDecimal amount;

}
