package com.future.repository;

import com.future.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String> {
    Transaction findById(String id);
}
