package com.future.controller;

import com.future.model.*;
import com.future.model.list.EmployeeItems;
import com.future.model.list.InventoryUsers;
import com.future.model.list.TransData;
import com.future.model.requestResponse.TransactionRequest;
import com.future.model.requestResponse.TransactionResponse;
import com.future.repository.EmployeeRepository;
import com.future.repository.InventoryRepository;
import com.future.repository.TransactionRepository;
import com.future.service.TransactionService;
import com.mongodb.BasicDBObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.util.List;

@RestController
//@CrossOrigin("*")
@RequestMapping("/api")
@CrossOrigin(origins = "*")
//public class TransactionController implements MongoTemplateRepository {
public class TransactionController {

   @Autowired
    TransactionService transactionService;

    @GetMapping("/transaction")
    public List<Transaction> getAllTransaction() {
        return transactionService.getAllTransaction();
    }

    @PostMapping("/transaction/List")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        return transactionService.createTransaction(transaction);
    }

    @PutMapping(value = "/transaction/assignment", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse assignment(@RequestBody TransactionRequest request) {
        return transactionService.assignment(request);
    }

    @PutMapping(value = "/transaction/return", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse returned(@RequestBody TransactionRequest request) {
        return transactionService.returned(request);
    }
}

