package com.chainverse.sentinel.audit.entity;

import com.chainverse.sentinel.shared.enums.DecisionType;
import com.chainverse.sentinel.transaction.entity.Transaction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "audit_logs")
@Getter
@Setter
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DecisionType decision;

    @Column(nullable = false)
    private Integer riskScore;

    @Column(nullable = false)
    private String riskLevel;

    @Column(nullable = false)
    private LocalDateTime auditTime;

    @Column(nullable = false)
    private String action;

    @Column(length = 64)
    private String blockchainHash;

}