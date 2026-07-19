package com.chainverse.sentinel.trust;

import com.chainverse.sentinel.beneficiary.entity.Beneficiary;
import com.chainverse.sentinel.shared.dto.EngineResult;

public interface TrustEngine {

    EngineResult analyze(Beneficiary beneficiary);

}