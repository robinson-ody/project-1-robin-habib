package com.future.controller;

import com.future.model.Assignment;
import com.future.model.Inventory;
import com.future.model.Transaction;
import com.future.repository.MongoTemplateRepository;
import com.future.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import java.security.PublicKey;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TransactionController implements MongoTemplateRepository {
    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    private MongoTemplate mongoTemplate; // we will use this to query mongoDb

    @Override
    public List<Transaction> findInventoryId(String inventoryId) {
       /* Query x = new Query();
        x.addCriteria(Criteria.where("transcData.inventoryId").is(inventoryId));
        x.fields().include("transcData.$");
        return mongoTemplate.find(x,Inventory.class);*/
       return null;
    }


    @GetMapping("/transaction")
    public List<Transaction>getAllTransaction()
    {
        return (List<Transaction>)transactionRepository.findAll();
    }

    @PostMapping("/transaction/create")
    public Transaction createTransaction(@RequestBody Transaction transaction){return transactionRepository.save(transaction);}

    @PutMapping("/transaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id")String id, @RequestBody Transaction transaction){
        Transaction transactionData = transactionRepository.findOne(id);
        if(transaction==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        else{
            transactionData.setTranscData(transaction.getTranscData());
            Transaction updatedTransaction = transactionRepository.save(transactionData);
            return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);


        }
    }

}
