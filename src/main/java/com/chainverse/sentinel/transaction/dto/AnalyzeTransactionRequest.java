package com.chainverse.sentinel.transaction.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AnalyzeTransactionRequest(

        @NotBlank
        String customerCode,

        @NotBlank
        String beneficiaryCode,

        @NotNull
        @DecimalMin("1.00")
        BigDecimal amount,

        @NotBlank
        String purpose,

        @NotBlank
        String deviceId,

        @NotBlank
        String location

) {
}