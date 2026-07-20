package com.chainverse.sentinel.health.dto;

public record HealthResponse(

        String application,

        String status,

        String database,

        String blockchain,

        String version

) {
}