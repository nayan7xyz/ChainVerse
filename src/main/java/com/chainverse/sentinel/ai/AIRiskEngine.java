package com.chainverse.sentinel.ai;

import com.chainverse.sentinel.shared.dto.EngineResult;

public interface AIRiskEngine {

    int calculateRiskScore(
            EngineResult behaviour,
            EngineResult trust,
            EngineResult fraudMemory
    );

}