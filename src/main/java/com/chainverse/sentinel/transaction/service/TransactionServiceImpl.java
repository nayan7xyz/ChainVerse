package com.chainverse.sentinel.transaction.service;

import com.chainverse.sentinel.orchestrator.DecisionOrchestrator;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionRequest;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final DecisionOrchestrator decisionOrchestrator;

    @Override
    public AnalyzeTransactionResponse analyzeTransaction(
            AnalyzeTransactionRequest request) {

        return decisionOrchestrator.analyzeTransaction(request);

    }

}