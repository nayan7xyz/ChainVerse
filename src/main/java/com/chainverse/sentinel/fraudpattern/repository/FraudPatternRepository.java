package com.chainverse.sentinel.fraudpattern.repository;

import com.chainverse.sentinel.fraudpattern.entity.FraudPattern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FraudPatternRepository extends JpaRepository<FraudPattern, Long> {
}