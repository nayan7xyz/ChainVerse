package com.chainverse.sentinel.dashboard.service;

import com.chainverse.sentinel.dashboard.dto.*;
import com.chainverse.sentinel.shared.enums.DecisionType;
import com.chainverse.sentinel.transaction.entity.Transaction;
import com.chainverse.sentinel.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final TransactionRepository transactionRepository;

    @Override
    public DashboardSummaryResponse getSummary() {

        Double average = transactionRepository.findAverageRiskScore();

        return new DashboardSummaryResponse(

                transactionRepository.count(),

                transactionRepository.countByDecision(DecisionType.APPROVED),

                transactionRepository.countByDecision(DecisionType.BLOCKED),

                transactionRepository.countByDecision(DecisionType.OTP_REQUIRED),

                transactionRepository.countByDecision(DecisionType.FACE_VERIFICATION),

                transactionRepository.countByDecision(DecisionType.COOLING_OFF),

                average == null ? 0.0 : average

        );

    }

    @Override
    public RiskDistributionResponse getRiskDistribution() {

        return new RiskDistributionResponse(

                transactionRepository.countByRiskLevel("LOW"),

                transactionRepository.countByRiskLevel("MEDIUM"),

                transactionRepository.countByRiskLevel("HIGH"),

                transactionRepository.countByRiskLevel("CRITICAL")

        );

    }

    @Override
    public List<RecentTransactionResponse> getRecentTransactions() {

        return transactionRepository
                .findTop10ByOrderByAnalysisTimeDesc()
                .stream()
                .map(this::convert)
                .toList();

    }
    @Override
    public DecisionDistributionResponse getDecisionDistribution() {

        return new DecisionDistributionResponse(

                transactionRepository.countByDecision(DecisionType.APPROVED),

                transactionRepository.countByDecision(DecisionType.BLOCKED),

                transactionRepository.countByDecision(DecisionType.OTP_REQUIRED),

                transactionRepository.countByDecision(DecisionType.FACE_VERIFICATION),

                transactionRepository.countByDecision(DecisionType.COOLING_OFF)

        );
    }

    @Override
    public List<RiskTrendResponse> getRiskTrend() {

        return transactionRepository
                .findTop10ByOrderByAnalysisTimeDesc()
                .stream()
                .map(t -> new RiskTrendResponse(

                        t.getAnalysisTime(),

                        t.getRiskScore()

                ))
                .toList();

    }

        private RecentTransactionResponse convert(Transaction transaction) {

        return new RecentTransactionResponse(

                transaction.getTransactionId(),

                transaction.getCustomer().getCustomerCode(),

                transaction.getBeneficiary().getBeneficiaryCode(),

                transaction.getAmount(),

                transaction.getRiskScore(),

                transaction.getRiskLevel(),

                transaction.getDecision(),

                transaction.getAnalysisTime()

        );

    }

}