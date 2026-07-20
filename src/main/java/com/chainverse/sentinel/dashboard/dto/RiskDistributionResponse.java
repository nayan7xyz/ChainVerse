package com.chainverse.sentinel.dashboard.dto;

public record RiskDistributionResponse(

        long low,

        long medium,

        long high,

        long critical

) {
}