package com.future.repository;

import com.future.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction,String> {
    Transaction findOneBy(String inventoryId);
}
