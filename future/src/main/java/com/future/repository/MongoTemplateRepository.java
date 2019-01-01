package com.future.repository;

import com.future.model.Transaction;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MongoTemplateRepository {

    // You will write your queries which will use mongoTemplate here.
    public List<Transaction> findInventoryId(String inventoryId);
}
