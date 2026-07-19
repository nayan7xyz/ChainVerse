package com.chainverse.sentinel.transaction.controller;

import com.chainverse.sentinel.shared.dto.ApiResponse;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionRequest;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionResponse;
import com.chainverse.sentinel.transaction.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/analyze")
    public ApiResponse<AnalyzeTransactionResponse> analyzeTransaction(
            @Valid @RequestBody AnalyzeTransactionRequest request) {

        return ApiResponse.success(
                transactionService.analyzeTransaction(request)
        );

    }

}