package com.chainverse.sentinel.transaction.dto;

import com.chainverse.sentinel.shared.enums.DecisionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionSummaryResponse(

        String transactionId,

        String customerCode,

        String beneficiaryCode,

        BigDecimal amount,

        Integer riskScore,

        String riskLevel,

        DecisionType decision,

        LocalDateTime analysisTime

) {
}