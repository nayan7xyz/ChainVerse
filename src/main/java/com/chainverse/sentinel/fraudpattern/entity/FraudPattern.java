package com.chainverse.sentinel.fraudpattern.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "fraud_patterns")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudPattern {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String patternName;

    @Column(nullable = false, length = 500)
    private String description;

    @Column(nullable = false)
    private Integer riskWeight;

    @Column(nullable = false)
    private Boolean active;

}