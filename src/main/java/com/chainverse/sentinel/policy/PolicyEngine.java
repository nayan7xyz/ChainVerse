package com.chainverse.sentinel.policy;

import com.chainverse.sentinel.shared.enums.DecisionType;

public interface PolicyEngine {

    DecisionType evaluate(int riskScore);

}