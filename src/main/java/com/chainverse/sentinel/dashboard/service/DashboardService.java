package com.chainverse.sentinel.dashboard.service;

import com.chainverse.sentinel.dashboard.dto.*;

import java.util.List;

public interface DashboardService {

    DashboardSummaryResponse getSummary();

    RiskDistributionResponse getRiskDistribution();

    List<RecentTransactionResponse> getRecentTransactions();
    DecisionDistributionResponse getDecisionDistribution();
    List<RiskTrendResponse> getRiskTrend();
}