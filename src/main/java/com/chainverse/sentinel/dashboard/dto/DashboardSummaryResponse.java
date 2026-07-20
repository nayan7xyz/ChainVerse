package com.chainverse.sentinel.dashboard.dto;

public record DashboardSummaryResponse(

        long totalTransactions,

        long approved,

        long blocked,

        long otpVerification,

        long faceVerification,

        long coolingPeriod,

        double averageRiskScore

) {
}