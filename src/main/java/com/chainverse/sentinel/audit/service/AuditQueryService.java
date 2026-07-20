package com.chainverse.sentinel.audit.service;

import com.chainverse.sentinel.audit.dto.AuditResponse;

public interface AuditQueryService {

    AuditResponse getAudit(String transactionId);

}