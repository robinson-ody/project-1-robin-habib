/*
package com.future.services;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import com.future.model.Inventory;
import com.future.model.Sequences;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.future.model.Sequences;
import java.util.Objects;

@Service
public class NextSequenceService implements CounterService {


        @Autowired
        MongoTemplate mongoTemplate;
        @Autowired CounterService counterService;

        public void saveUser(){
            Inventory inventory = new Inventory();
            inventory.setInventoryId(counterService.getNextUserIdSequence());
            mongoTemplate.save(inventory);
        }

}*/
