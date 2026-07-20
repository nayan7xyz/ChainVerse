package com.chainverse.sentinel.audit.repository;

import com.chainverse.sentinel.audit.entity.AuditLog;
import com.chainverse.sentinel.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuditLogRepository extends JpaRepository<AuditLog, Long> {

    Optional<AuditLog> findByTransaction(Transaction transaction);

}