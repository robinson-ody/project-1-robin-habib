package com.future.repository;

import com.future.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TransactionRepository extends MongoRepository<Transaction,String>, MongoTemplateRepository {
    @Query(value = "{'transData':{$all : [?0}}")
    List<Transaction> findInventoryId(String inventoryId);
}
