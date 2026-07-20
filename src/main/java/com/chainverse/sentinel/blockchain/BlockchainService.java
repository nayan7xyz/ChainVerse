package com.chainverse.sentinel.blockchain;

import com.chainverse.sentinel.transaction.entity.Transaction;

public interface BlockchainService {

    String generateHash(Transaction transaction);

}