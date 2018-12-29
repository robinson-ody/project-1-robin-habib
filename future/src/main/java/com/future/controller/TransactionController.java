package com.future.controller;

import com.future.model.Assignment;
import com.future.model.Transaction;
import com.future.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.PublicKey;
import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    TransactionRepository transactionRepository;

    @GetMapping("/transaction")
    public List<Transaction>getAllTransaction(){
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
        transactionData.setStatus(transaction.getStatus());
        Transaction updatedTransaction = transactionRepository.save(transactionData);
        return new ResponseEntity<>(updatedTransaction, HttpStatus.OK);
    }

}
