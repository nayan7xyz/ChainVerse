package com.chainverse.sentinel.fraudmemory;

import com.chainverse.sentinel.beneficiary.entity.Beneficiary;
import com.chainverse.sentinel.shared.dto.EngineResult;
import org.springframework.stereotype.Service;

@Service
public class FraudMemoryEngineImpl implements FraudMemoryEngine {

    @Override
    public EngineResult analyze(Beneficiary beneficiary) {

        EngineResult result = EngineResult.builder()
                .score(0)
                .build();

        // MVP Simulation
        if (Boolean.TRUE.equals(beneficiary.getFraudReported())) {

            result.setScore(30);
            result.getReasons().add("Beneficiary found in Fraud Memory Network");

        }

        return result;
    }
}