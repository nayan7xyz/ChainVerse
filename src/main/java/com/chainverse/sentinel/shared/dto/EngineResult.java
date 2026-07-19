package com.chainverse.sentinel.shared.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EngineResult {

    private Integer score;

    @Builder.Default
    private List<String> reasons = new ArrayList<>();

}