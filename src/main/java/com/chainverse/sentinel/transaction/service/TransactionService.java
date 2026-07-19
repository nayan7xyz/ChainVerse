package com.chainverse.sentinel.transaction.service;

import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionRequest;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionResponse;

public interface TransactionService {

    AnalyzeTransactionResponse analyzeTransaction(
            AnalyzeTransactionRequest request
    );

}