package com.chainverse.sentinel.transaction.service;

import com.chainverse.sentinel.exception.ResourceNotFoundException;
import com.chainverse.sentinel.transaction.dto.TransactionDetailsResponse;
import com.chainverse.sentinel.transaction.dto.TransactionSummaryResponse;
import com.chainverse.sentinel.transaction.entity.Transaction;
import com.chainverse.sentinel.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionQueryServiceImpl implements TransactionQueryService {

    private final TransactionRepository transactionRepository;

    @Override
    public List<TransactionSummaryResponse> getAllTransactions() {

        return transactionRepository.findAllByOrderByAnalysisTimeDesc()
                .stream()
                .map(this::toSummary)
                .toList();

    }

    @Override
    public TransactionDetailsResponse getTransaction(String transactionId) {

        Transaction transaction = transactionRepository
                .findByTransactionId(transactionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Transaction not found : " + transactionId));

        return toDetails(transaction);

    }

    private TransactionSummaryResponse toSummary(Transaction t) {

        return new TransactionSummaryResponse(

                t.getTransactionId(),

                t.getCustomer().getCustomerCode(),

                t.getBeneficiary().getBeneficiaryCode(),

                t.getAmount(),

                t.getRiskScore(),

                t.getRiskLevel(),

                t.getDecision(),

                t.getAnalysisTime()

        );

    }

    private TransactionDetailsResponse toDetails(Transaction t) {

        return new TransactionDetailsResponse(

                t.getTransactionId(),

                t.getCustomer().getCustomerCode(),

                t.getBeneficiary().getBeneficiaryCode(),

                t.getAmount(),

                t.getPurpose(),

                t.getDeviceId(),

                t.getLocation(),

                t.getRiskScore(),

                t.getRiskLevel(),

                t.getDecision(),

                t.getStatus(),

                t.getReasons(),

                t.getBlockchainHash(),

                t.getTransactionTime(),

                t.getAnalysisTime()

        );

    }

}