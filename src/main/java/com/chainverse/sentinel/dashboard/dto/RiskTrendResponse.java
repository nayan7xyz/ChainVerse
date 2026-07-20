package com.chainverse.sentinel.dashboard.dto;

import java.time.LocalDateTime;

public record RiskTrendResponse(

        LocalDateTime analysisTime,

        Integer riskScore

) {
}