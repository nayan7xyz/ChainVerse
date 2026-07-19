package com.chainverse.sentinel.orchestrator;

import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionRequest;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionResponse;

public interface DecisionOrchestrator {

    AnalyzeTransactionResponse analyzeTransaction(
            AnalyzeTransactionRequest request
    );

}