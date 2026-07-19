package com.chainverse.sentinel.dashboard.controller;

import com.chainverse.sentinel.shared.constants.ApiConstants;
import com.chainverse.sentinel.shared.util.ResponseUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ApiConstants.API_V1 + "/health")
public class HealthController {

    @GetMapping
    public Object health() {

        return ResponseUtil.success(
                "ChainVerse Backend is Running");

    }

}