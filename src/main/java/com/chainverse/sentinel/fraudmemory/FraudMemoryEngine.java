package com.chainverse.sentinel.fraudmemory;

import com.chainverse.sentinel.beneficiary.entity.Beneficiary;
import com.chainverse.sentinel.shared.dto.EngineResult;

public interface FraudMemoryEngine {

    EngineResult analyze(Beneficiary beneficiary);

}