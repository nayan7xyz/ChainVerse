package com.chainverse.sentinel.audit.service;

import com.chainverse.sentinel.transaction.entity.Transaction;

public interface AuditService {

    void saveAudit(Transaction transaction);

}