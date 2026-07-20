package com.chainverse.sentinel.transaction.entity;

import com.chainverse.sentinel.beneficiary.entity.Beneficiary;
import com.chainverse.sentinel.customer.entity.Customer;
import com.chainverse.sentinel.shared.enums.DecisionType;
import com.chainverse.sentinel.shared.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "beneficiary_id", nullable = false)
    private Beneficiary beneficiary;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private String purpose;

    @Column(nullable = false)
    private String deviceId;

    @Column(nullable = false)
    private String location;

    @Column(nullable = false)
    private LocalDateTime transactionTime;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DecisionType decision;

    @Column(nullable = false)
    private Integer riskScore;

    @Column(nullable = false)
    private String riskLevel;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "transaction_reasons",
            joinColumns = @JoinColumn(name = "transaction_id")
    )
    @Column(name = "reason")
    @Builder.Default
    private List<String> reasons = new ArrayList<>();

    @Column(nullable = false)
    private LocalDateTime analysisTime;

    @Column(length = 64)
    private String blockchainHash;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {

        LocalDateTime now = LocalDateTime.now();

        this.createdAt = now;
        this.updatedAt = now;

        if (this.transactionTime == null) {
            this.transactionTime = now;
        }

        if (this.analysisTime == null) {
            this.analysisTime = now;
        }

    }

    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

}