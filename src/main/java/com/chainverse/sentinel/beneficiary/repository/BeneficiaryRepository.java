package com.chainverse.sentinel.beneficiary.repository;

import com.chainverse.sentinel.beneficiary.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {

    Optional<Beneficiary> findByBeneficiaryCode(String beneficiaryCode);

}