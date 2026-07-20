package com.chainverse.sentinel.transaction.dto;

import com.chainverse.sentinel.shared.enums.DecisionType;
import com.chainverse.sentinel.shared.enums.TransactionStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record TransactionDetailsResponse(

        String transactionId,

        String customerCode,

        String beneficiaryCode,

        BigDecimal amount,

        String purpose,

        String deviceId,

        String location,

        Integer riskScore,

        String riskLevel,

        DecisionType decision,

        TransactionStatus status,

        List<String> reasons,

        String blockchainHash,

        LocalDateTime transactionTime,

        LocalDateTime analysisTime

) {
}