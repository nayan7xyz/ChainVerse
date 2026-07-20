package com.chainverse.sentinel.transaction.service;

import com.chainverse.sentinel.transaction.dto.TransactionDetailsResponse;
import com.chainverse.sentinel.transaction.dto.TransactionSummaryResponse;

import java.util.List;

public interface TransactionQueryService {

    List<TransactionSummaryResponse> getAllTransactions();

    TransactionDetailsResponse getTransaction(String transactionId);

}