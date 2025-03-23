package com.github.tennyros.eventmodels.events;

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
public class WithdrawalRequestedEvent {

    private String senderId;
    private String recipientId;
    private BigDecimal amount;

}
