package com.chainverse.sentinel.dashboard.dto;

public record DecisionDistributionResponse(

        long approved,

        long blocked,

        long otpRequired,

        long faceVerification,

        long coolingOff

) {
}