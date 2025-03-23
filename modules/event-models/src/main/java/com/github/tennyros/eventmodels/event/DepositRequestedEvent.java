package com.github.tennyros.eventmodels.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DepositRequestedEvent {

    private String senderId;
    private String recipientId;
    private BigDecimal amount;

}
