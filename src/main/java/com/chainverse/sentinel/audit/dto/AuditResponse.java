package com.chainverse.sentinel.audit.dto;

import com.chainverse.sentinel.shared.enums.DecisionType;

import java.time.LocalDateTime;

public record AuditResponse(

        String transactionId,

        DecisionType decision,

        Integer riskScore,

        String riskLevel,

        LocalDateTime auditTime,

        String action,

        String blockchainHash

) {
}