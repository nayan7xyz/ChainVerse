package com.chainverse.sentinel.audit.service;

import com.chainverse.sentinel.audit.entity.AuditLog;
import com.chainverse.sentinel.audit.repository.AuditLogRepository;
import com.chainverse.sentinel.transaction.entity.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuditServiceImpl implements AuditService {

    private final AuditLogRepository auditLogRepository;

    @Override
    public void saveAudit(Transaction transaction) {

        AuditLog auditLog = new AuditLog();

        auditLog.setTransaction(transaction);
        auditLog.setDecision(transaction.getDecision());
        auditLog.setRiskScore(transaction.getRiskScore());
        auditLog.setRiskLevel(transaction.getRiskLevel());
        auditLog.setAuditTime(LocalDateTime.now());

        auditLog.setAction(
                transaction.getDecision().name()
        );

        auditLogRepository.save(auditLog);

    }

}