package com.future.controller;

import com.future.model.*;
import com.future.repository.EmployeeRepository;
import com.future.repository.InventoryRepository;
import com.future.repository.TransactionRepository;
import com.mongodb.WriteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.objects.Global.print;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
//public class TransactionController implements MongoTemplateRepository {
    public class TransactionController {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    InventoryRepository inventoryRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private MongoTemplate mongoTemplate; // we will use this to query mongoDb

    @GetMapping("/transaction")
    public List<Transaction> getAllTransaction() {
        return (List<Transaction>) transactionRepository.findAll();
    }

    @PostMapping("/transaction/List")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @PostMapping(value = "/transaction/assignment", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse authenticate(@RequestBody TransactionRequest request) {
        TransactionResponse t = new TransactionResponse();
        Employee employeeData = employeeRepository.findByEmail(request.getEmail());
        Inventory inventoryData = inventoryRepository.findByInventoryId(request.getInventoryId());
        Transaction transactionData = transactionRepository.findById(request.getId());
        List<TransData> transaction = transactionData.getTranscData();
        System.out.println(transaction);
        if (transaction==null){
            t.setSuccess("Transaction NULL");
            return t;
        }
        for (int i = 0; i < transaction.size(); i++) {
            if (transaction.get(i).getInventoryId().equals(inventoryData.getInventoryId())) {
                inventoryData.setStock(inventoryData.getStock()-transaction.get(i).getQty());
                inventoryRepository.save(inventoryData);
                t.setSuccess("Transaction SUCCESS");
                return t;
            }
        }
        t.setSuccess("Transaction FAILED");
        return t;
    }

}


/* transactionRepository.save(transaction);
        Transaction transactionData = transactionRepository.findById(transaction.getId());
        List<TransData> reduce = transactionData.getTranscData();
        transactionData.setStatus("Pending");
        for (int i = 0; i < reduce.size(); i++) {
            Inventory inventoryData = inventoryRepository.findByInventoryId(reduce.get(i).getInventoryId());
            if (reduce.get(i).getInventoryId().equals(inventoryData.getInventoryId())){
                inventoryData.setStock(reduce.get(i).getQty());
                System.out.println(inventoryData.getStock());
                System.out.println(reduce.get(i).getQty());
                System.out.println("MASUKKKKKK");
            }
            else {
                return null;
            }
        }*/