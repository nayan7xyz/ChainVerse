package com.chainverse.sentinel.audit.controller;

import com.chainverse.sentinel.audit.dto.AuditResponse;
import com.chainverse.sentinel.audit.service.AuditQueryService;
import com.chainverse.sentinel.shared.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/audit")
@RequiredArgsConstructor
public class AuditController {

    private final AuditQueryService auditQueryService;

    @GetMapping("/{transactionId}")
    public ApiResponse<AuditResponse> getAudit(
            @PathVariable String transactionId) {

        return ApiResponse.success(
                auditQueryService.getAudit(transactionId)
        );

    }

}