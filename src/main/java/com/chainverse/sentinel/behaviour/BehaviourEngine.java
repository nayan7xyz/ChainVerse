package com.chainverse.sentinel.behaviour;

import com.chainverse.sentinel.customer.entity.Customer;
import com.chainverse.sentinel.shared.dto.EngineResult;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionRequest;

public interface BehaviourEngine {

    EngineResult analyze(Customer customer,
                         AnalyzeTransactionRequest request);

}