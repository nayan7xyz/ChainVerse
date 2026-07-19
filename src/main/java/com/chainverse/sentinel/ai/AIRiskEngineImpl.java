package com.chainverse.sentinel.ai;

import com.chainverse.sentinel.shared.dto.EngineResult;
import org.springframework.stereotype.Service;

@Service
public class AIRiskEngineImpl implements AIRiskEngine {

    @Override
    public int calculateRiskScore(
            EngineResult behaviour,
            EngineResult trust,
            EngineResult fraudMemory) {

        int score =
                behaviour.getScore()
                        + trust.getScore()
                        + fraudMemory.getScore();

        return Math.min(score, 100);

    }
}