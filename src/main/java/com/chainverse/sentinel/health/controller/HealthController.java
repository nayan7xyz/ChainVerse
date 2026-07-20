package com.chainverse.sentinel.health.controller;

import com.chainverse.sentinel.health.dto.HealthResponse;
import com.chainverse.sentinel.shared.dto.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/health")
public class HealthController {

    @GetMapping
    public ApiResponse<HealthResponse> health() {

        return ApiResponse.success(

                new HealthResponse(

                        "ChainVerse Sentinel",

                        "UP",

                        "CONNECTED",

                        "ACTIVE",

                        "1.0.0"

                )

        );

    }

}