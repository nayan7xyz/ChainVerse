package com.chainverse.sentinel.beneficiary.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "beneficiaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beneficiary {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String beneficiaryCode;

    @Column(nullable = false)
    private String beneficiaryName;

    @Column(nullable = false)
    private Integer trustScore;

    @Column(nullable = false)
    private Boolean fraudReported;

    @Column(nullable = false)
    private Integer accountAgeInMonths;

    @Column(nullable = false)
    private Integer successfulTransactions;

}