package com.chainverse.sentinel.simulation.loader;

import com.chainverse.sentinel.beneficiary.entity.Beneficiary;
import com.chainverse.sentinel.beneficiary.repository.BeneficiaryRepository;
import com.chainverse.sentinel.customer.entity.Customer;
import com.chainverse.sentinel.customer.repository.CustomerRepository;
import com.chainverse.sentinel.fraudpattern.entity.FraudPattern;
import com.chainverse.sentinel.fraudpattern.repository.FraudPatternRepository;
import com.chainverse.sentinel.simulation.dto.TransactionSeed;
import com.chainverse.sentinel.transaction.entity.Transaction;
import com.chainverse.sentinel.transaction.repository.TransactionRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import java.io.InputStream;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class SimulationDataLoader implements CommandLineRunner {

    private final ObjectMapper objectMapper;

    private final CustomerRepository customerRepository;
    private final BeneficiaryRepository beneficiaryRepository;
    private final FraudPatternRepository fraudPatternRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public void run(String... args) throws Exception {

        if (customerRepository.count() > 0) {
            log.info("Simulation data already loaded.");
            return;
        }

        loadCustomers();

        loadBeneficiaries();

        loadFraudPatterns();

        loadTransactions();

        log.info("=========================================");
        log.info("Simulation Data Loaded Successfully");
        log.info("Customers       : {}", customerRepository.count());
        log.info("Beneficiaries   : {}", beneficiaryRepository.count());
        log.info("Fraud Patterns  : {}", fraudPatternRepository.count());
        log.info("Transactions    : {}", transactionRepository.count());
        log.info("=========================================");

    }

    private void loadCustomers() throws Exception {

        InputStream inputStream =
                new ClassPathResource("simulation-data/customers.json")
                        .getInputStream();

        List<Customer> customers =
                objectMapper.readValue(
                        inputStream,
                        new TypeReference<List<Customer>>() {
                        });

        customerRepository.saveAll(customers);

        log.info("{} Customers Loaded", customers.size());

    }

    private void loadBeneficiaries() throws Exception {

        InputStream inputStream =
                new ClassPathResource("simulation-data/beneficiaries.json")
                        .getInputStream();

        List<Beneficiary> beneficiaries =
                objectMapper.readValue(
                        inputStream,
                        new TypeReference<List<Beneficiary>>() {
                        });

        beneficiaryRepository.saveAll(beneficiaries);

        log.info("{} Beneficiaries Loaded", beneficiaries.size());

    }

    private void loadFraudPatterns() throws Exception {

        InputStream inputStream =
                new ClassPathResource("simulation-data/fraud-patterns.json")
                        .getInputStream();

        List<FraudPattern> patterns =
                objectMapper.readValue(
                        inputStream,
                        new TypeReference<List<FraudPattern>>() {
                        });

        fraudPatternRepository.saveAll(patterns);

        log.info("{} Fraud Patterns Loaded", patterns.size());

    }
    private void loadTransactions() throws Exception {

        InputStream inputStream =
                new ClassPathResource("simulation-data/transactions.json")
                        .getInputStream();

        List<TransactionSeed> transactionSeeds =
                objectMapper.readValue(
                        inputStream,
                        new TypeReference<List<TransactionSeed>>() {
                        });

        for (TransactionSeed seed : transactionSeeds) {

            Customer customer = customerRepository
                    .findByCustomerCode(seed.customerCode())
                    .orElseThrow(() -> new RuntimeException(
                            "Customer not found : " + seed.customerCode()));

            Beneficiary beneficiary = beneficiaryRepository
                    .findByBeneficiaryCode(seed.beneficiaryCode())
                    .orElseThrow(() -> new RuntimeException(
                            "Beneficiary not found : " + seed.beneficiaryCode()));

            Transaction transaction = Transaction.builder()
                    .transactionId(seed.transactionId())
                    .customer(customer)
                    .beneficiary(beneficiary)
                    .amount(seed.amount())
                    .purpose(seed.purpose())
                    .deviceId(seed.deviceId())
                    .location(seed.location())
                    .transactionTime(seed.transactionTime())
                    .status(seed.status())
                    .decision(seed.decision())
                    .riskScore(seed.riskScore())
                    .build();

            transactionRepository.save(transaction);

        }

        log.info("{} Transactions Loaded", transactionSeeds.size());

    }

}