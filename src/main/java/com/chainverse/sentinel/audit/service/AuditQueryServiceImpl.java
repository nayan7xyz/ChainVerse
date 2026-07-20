package com.chainverse.sentinel.audit.service;

import com.chainverse.sentinel.audit.dto.AuditResponse;
import com.chainverse.sentinel.audit.entity.AuditLog;
import com.chainverse.sentinel.audit.repository.AuditLogRepository;
import com.chainverse.sentinel.exception.ResourceNotFoundException;
import com.chainverse.sentinel.transaction.entity.Transaction;
import com.chainverse.sentinel.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuditQueryServiceImpl implements AuditQueryService {

    private final TransactionRepository transactionRepository;
    private final AuditLogRepository auditLogRepository;

    @Override
    public AuditResponse getAudit(String transactionId) {

        Transaction transaction = transactionRepository
                .findByTransactionId(transactionId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Transaction not found : " + transactionId));

        AuditLog audit = auditLogRepository
                .findByTransaction(transaction)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Audit not found"));

        return new AuditResponse(

                transaction.getTransactionId(),

                audit.getDecision(),

                audit.getRiskScore(),

                audit.getRiskLevel(),

                audit.getAuditTime(),

                audit.getAction(),

                audit.getBlockchainHash()

        );

    }

}