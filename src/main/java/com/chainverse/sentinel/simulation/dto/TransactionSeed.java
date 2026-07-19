package com.chainverse.sentinel.simulation.dto;

import com.chainverse.sentinel.shared.enums.DecisionType;
import com.chainverse.sentinel.shared.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionSeed(

        String transactionId,

        String customerCode,

        String beneficiaryCode,

        BigDecimal amount,

        String purpose,

        String deviceId,

        String location,

        LocalDateTime transactionTime,

        TransactionStatus status,

        DecisionType decision,

        Integer riskScore

) {
}