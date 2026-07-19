package com.chainverse.sentinel.transaction.dto;

import com.chainverse.sentinel.shared.enums.DecisionType;

import java.time.LocalDateTime;
import java.util.List;

public record AnalyzeTransactionResponse(

        String transactionId,

        LocalDateTime analysisTime,

        Integer riskScore,

        String riskLevel,

        DecisionType decision,

        List<String> reasons

) {
}