package com.chainverse.sentinel.blockchain;

import com.chainverse.sentinel.audit.entity.AuditLog;
import com.chainverse.sentinel.audit.repository.AuditLogRepository;
import com.chainverse.sentinel.transaction.entity.Transaction;
import com.chainverse.sentinel.transaction.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockchainServiceImpl implements BlockchainService {

    private final TransactionRepository transactionRepository;
    private final AuditLogRepository auditLogRepository;

    @Override
    public String generateHash(Transaction transaction) {

        String payload =
                transaction.getTransactionId()
                        + transaction.getRiskScore()
                        + transaction.getDecision()
                        + transaction.getAnalysisTime();

        String hash = HashUtil.sha256(payload);

        transaction.setBlockchainHash(hash);

        transactionRepository.save(transaction);

        auditLogRepository.findByTransaction(transaction)
                .ifPresent(audit -> {

                    audit.setBlockchainHash(hash);

                    auditLogRepository.save(audit);

                });

        return hash;

    }

}