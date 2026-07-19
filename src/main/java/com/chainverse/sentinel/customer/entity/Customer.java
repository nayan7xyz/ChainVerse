package com.chainverse.sentinel.customer.entity;

import com.chainverse.sentinel.shared.enums.RiskProfile;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String customerCode;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private BigDecimal balance;

    @Enumerated(EnumType.STRING)
    @Column(nullable =false)
    private RiskProfile riskProfile;

    @Column(nullable = false)
    private BigDecimal averageTransactionAmount;

    @Column(nullable = false)
    private String homeLocation;

    @Column(nullable = false)
    private String registeredDevice;

}