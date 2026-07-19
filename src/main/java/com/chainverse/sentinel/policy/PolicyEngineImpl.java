package com.chainverse.sentinel.policy;

import com.chainverse.sentinel.shared.enums.DecisionType;
import org.springframework.stereotype.Service;

@Service
public class PolicyEngineImpl implements PolicyEngine {

    @Override
    public DecisionType evaluate(int riskScore) {

        if (riskScore <= 30)
            return DecisionType.APPROVED;

        if (riskScore <= 60)
            return DecisionType.OTP_REQUIRED;

        if (riskScore <= 80)
            return DecisionType.FACE_VERIFICATION;

        if (riskScore <= 90)
            return DecisionType.COOLING_OFF;

        return DecisionType.BLOCKED;
    }
}