package com.chainverse.sentinel.transaction.repository;

import com.chainverse.sentinel.customer.entity.Customer;
import com.chainverse.sentinel.shared.enums.DecisionType;
import com.chainverse.sentinel.transaction.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    Optional<Transaction> findByTransactionId(String transactionId);

    List<Transaction> findByCustomer(Customer customer);

    long countByDecision(DecisionType decision);

    long countByRiskLevel(String riskLevel);

    List<Transaction> findTop10ByOrderByAnalysisTimeDesc();

    @Query("SELECT AVG(t.riskScore) FROM Transaction t")
    Double findAverageRiskScore();

    // Transaction History
    List<Transaction> findAllByOrderByAnalysisTimeDesc();

}