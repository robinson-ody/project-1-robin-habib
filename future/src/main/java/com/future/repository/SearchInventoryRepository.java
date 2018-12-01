/*
package com.future.repository;

import com.future.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
@Repository
public class SearchInventoryRepository {
    @Autowired
    MongoTemplate mongoTemplate;
    public Collection<Inventory> searchInventory(String text) {
        return mongoTemplate.find(Query.query(new Criteria()
                .orOperator(Criteria.where("inventoryId").regex(text, "i"),
                        Criteria.where("detail").regex(text, "i"))
        ), Inventory.class);
    }

}
*/
