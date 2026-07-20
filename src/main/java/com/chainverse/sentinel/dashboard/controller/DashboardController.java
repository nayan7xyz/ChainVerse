package com.chainverse.sentinel.dashboard.controller;

import com.chainverse.sentinel.dashboard.dto.*;
import com.chainverse.sentinel.dashboard.service.DashboardService;
import com.chainverse.sentinel.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/summary")
    public ApiResponse<DashboardSummaryResponse> getSummary() {

        return ApiResponse.success(
                dashboardService.getSummary()
        );

    }

    @GetMapping("/decision-distribution")
    public ApiResponse<DecisionDistributionResponse> getDecisionDistribution() {

        return ApiResponse.success(
                dashboardService.getDecisionDistribution()
        );

    }

    @GetMapping("/risk-trend")
    public ApiResponse<List<RiskTrendResponse>> getRiskTrend() {

        return ApiResponse.success(
                dashboardService.getRiskTrend()
        );

    }

    @GetMapping("/risk-distribution")
    public ApiResponse<RiskDistributionResponse> getRiskDistribution() {

        return ApiResponse.success(
                dashboardService.getRiskDistribution()
        );

    }

    @GetMapping("/recent-transactions")
    public ApiResponse<List<RecentTransactionResponse>> getRecentTransactions() {

        return ApiResponse.success(
                dashboardService.getRecentTransactions()
        );

    }

}