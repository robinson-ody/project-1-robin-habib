package com.future.controller;

import com.future.model.Inventory;
import com.future.model.Transaction;
import com.future.repository.MongoTemplateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;

import javax.management.Query;
import java.util.List;

public class TransactionImpl implements MongoTemplateRepository {

    @Autowired
    private MongoTemplate mongoTemplate; // we will use this to query mongoDb

    @Override
    public List<Transaction> findInventoryId(String inventoryId) {
        Query x = new Query();
        x.addCriteria(Criteria.where("transcData.inventoryId").is(inventoryId));
        x.fields().include("transcData.$");
        return mongoTemplate.find(x, Inventory.class);
    }
}
