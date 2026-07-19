package com.chainverse.sentinel.shared.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class ErrorResponse {

    private boolean success;

    private String message;

    private String errorCode;

    private LocalDateTime timestamp;

    private List<String> errors;

}