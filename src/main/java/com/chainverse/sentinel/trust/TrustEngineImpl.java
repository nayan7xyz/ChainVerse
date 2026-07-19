package com.chainverse.sentinel.trust;

import com.chainverse.sentinel.beneficiary.entity.Beneficiary;
import com.chainverse.sentinel.shared.dto.EngineResult;
import org.springframework.stereotype.Service;

@Service
public class TrustEngineImpl implements TrustEngine {

    @Override
    public EngineResult analyze(Beneficiary beneficiary) {

        EngineResult result = EngineResult.builder()
                .score(0)
                .build();

        // Rule 1 - Low Trust Score
        if (beneficiary.getTrustScore() < 30) {

            result.setScore(result.getScore() + 40);
            result.getReasons().add("Beneficiary has a very low trust score");

        } else if (beneficiary.getTrustScore() < 60) {

            result.setScore(result.getScore() + 20);
            result.getReasons().add("Beneficiary has a moderate trust score");
        }

        // Rule 2 - Fraud Reported
        if (Boolean.TRUE.equals(beneficiary.getFraudReported())) {

            result.setScore(result.getScore() + 50);
            result.getReasons().add("Beneficiary has previous fraud reports");
        }

        // Rule 3 - New Account
        if (beneficiary.getAccountAgeInMonths() < 6) {

            result.setScore(result.getScore() + 20);
            result.getReasons().add("Beneficiary account is newly created");
        }

        // Rule 4 - Very Few Successful Transactions
        if (beneficiary.getSuccessfulTransactions() < 10) {

            result.setScore(result.getScore() + 15);
            result.getReasons().add("Beneficiary has very limited transaction history");
        }

        return result;
    }
}