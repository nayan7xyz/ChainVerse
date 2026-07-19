package com.chainverse.sentinel.behaviour;

import com.chainverse.sentinel.customer.entity.Customer;
import com.chainverse.sentinel.shared.dto.EngineResult;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionRequest;
import org.springframework.stereotype.Service;

@Service
public class BehaviourEngineImpl implements BehaviourEngine {

    @Override
    public EngineResult analyze(Customer customer,
                                AnalyzeTransactionRequest request) {

        EngineResult result = EngineResult.builder()
                .score(0)
                .build();

        // Rule 1 - High Amount
        if (request.amount().compareTo(
                customer.getAverageTransactionAmount().multiply(
                        java.math.BigDecimal.valueOf(5))) > 0) {

            result.setScore(result.getScore() + 30);
            result.getReasons().add("Transaction amount is unusually high");
        }

        // Rule 2 - New Location
        if (!customer.getHomeLocation()
                .equalsIgnoreCase(request.location())) {

            result.setScore(result.getScore() + 20);
            result.getReasons().add("Transaction from a different location");
        }

        // Rule 3 - New Device
        if (!customer.getRegisteredDevice()
                .equalsIgnoreCase(request.deviceId())) {

            result.setScore(result.getScore() + 25);
            result.getReasons().add("Transaction initiated from a new device");
        }

        return result;
    }

}