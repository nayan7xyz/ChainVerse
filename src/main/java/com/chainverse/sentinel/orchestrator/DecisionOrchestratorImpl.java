package com.chainverse.sentinel.orchestrator;

import com.chainverse.sentinel.ai.AIRiskEngine;
import com.chainverse.sentinel.audit.service.AuditService;
import com.chainverse.sentinel.behaviour.BehaviourEngine;
import com.chainverse.sentinel.beneficiary.entity.Beneficiary;
import com.chainverse.sentinel.beneficiary.repository.BeneficiaryRepository;
import com.chainverse.sentinel.blockchain.BlockchainService;
import com.chainverse.sentinel.customer.entity.Customer;
import com.chainverse.sentinel.customer.repository.CustomerRepository;
import com.chainverse.sentinel.fraudmemory.FraudMemoryEngine;
import com.chainverse.sentinel.policy.PolicyEngine;
import com.chainverse.sentinel.shared.dto.EngineResult;
import com.chainverse.sentinel.shared.enums.DecisionType;
import com.chainverse.sentinel.shared.enums.TransactionStatus;
import com.chainverse.sentinel.exception.ResourceNotFoundException;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionRequest;
import com.chainverse.sentinel.transaction.dto.AnalyzeTransactionResponse;
import com.chainverse.sentinel.transaction.entity.Transaction;
import com.chainverse.sentinel.transaction.repository.TransactionRepository;
import com.chainverse.sentinel.trust.TrustEngine;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DecisionOrchestratorImpl implements DecisionOrchestrator {

    private final CustomerRepository customerRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final TransactionRepository transactionRepository;
    private final AuditService auditService;
    private final BlockchainService blockchainService;

    private final BehaviourEngine behaviourEngine;
    private final TrustEngine trustEngine;
    private final FraudMemoryEngine fraudMemoryEngine;
    private final AIRiskEngine aiRiskEngine;
    private final PolicyEngine policyEngine;

    @Override
    public AnalyzeTransactionResponse analyzeTransaction(
            AnalyzeTransactionRequest request) {

        Customer customer = customerRepository
                .findByCustomerCode(request.customerCode())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Customer not found : " + request.customerCode()));

        Beneficiary beneficiary = beneficiaryRepository
                .findByBeneficiaryCode(request.beneficiaryCode())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Beneficiary not found : " + request.beneficiaryCode()));

        EngineResult behaviour =
                behaviourEngine.analyze(customer, request);

        EngineResult trust =
                trustEngine.analyze(beneficiary);

        EngineResult fraudMemory =
                fraudMemoryEngine.analyze(beneficiary);

        int riskScore =
                aiRiskEngine.calculateRiskScore(
                        behaviour,
                        trust,
                        fraudMemory);

        DecisionType decision =
                policyEngine.evaluate(riskScore);

        String riskLevel = getRiskLevel(riskScore);

        List<String> reasons = new ArrayList<>();
        reasons.addAll(behaviour.getReasons());
        reasons.addAll(trust.getReasons());
        reasons.addAll(fraudMemory.getReasons());

        String transactionId = UUID.randomUUID().toString();

        Transaction transaction = Transaction.builder()
                .transactionId(transactionId)
                .customer(customer)
                .beneficiary(beneficiary)
                .amount(request.amount())
                .purpose(request.purpose())
                .deviceId(request.deviceId())
                .location(request.location())
                .transactionTime(LocalDateTime.now())
                .analysisTime(LocalDateTime.now())
                .status(TransactionStatus.COMPLETED)
                .decision(decision)
                .riskScore(riskScore)
                .riskLevel(riskLevel)
                .reasons(reasons)
                .build();
           transactionRepository.save(transaction);
           auditService.saveAudit(transaction);
           blockchainService.generateHash(transaction);

        return new AnalyzeTransactionResponse(
                transactionId,
                transaction.getAnalysisTime(),
                riskScore,
                riskLevel,
                decision,
                reasons
        );

    }

    private String getRiskLevel(int score) {

        if (score <= 30)
            return "LOW";

        if (score <= 60)
            return "MEDIUM";

        if (score <= 80)
            return "HIGH";

        return "CRITICAL";
    }

}