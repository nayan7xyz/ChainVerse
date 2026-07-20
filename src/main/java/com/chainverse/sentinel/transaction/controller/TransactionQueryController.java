package com.chainverse.sentinel.transaction.controller;

import com.chainverse.sentinel.shared.dto.ApiResponse;
import com.chainverse.sentinel.transaction.dto.TransactionDetailsResponse;
import com.chainverse.sentinel.transaction.dto.TransactionSummaryResponse;
import com.chainverse.sentinel.transaction.service.TransactionQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionQueryController {

    private final TransactionQueryService transactionQueryService;

    @GetMapping
    public ApiResponse<List<TransactionSummaryResponse>> getTransactions() {

        return ApiResponse.success(
                transactionQueryService.getAllTransactions()
        );

    }

    @GetMapping("/{transactionId}")
    public ApiResponse<TransactionDetailsResponse> getTransaction(
            @PathVariable String transactionId) {

        return ApiResponse.success(
                transactionQueryService.getTransaction(transactionId)
        );

    }

}