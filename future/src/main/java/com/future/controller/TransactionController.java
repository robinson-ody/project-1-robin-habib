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

    @PutMapping("/transaction/{id}")
    public ResponseEntity<Transaction> updateTransaction(@PathVariable("id") String id, @RequestBody Transaction transaction) {
        Transaction transactionData = transactionRepository.findOne(id);
        if (transaction == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transactionData.setStatus(transaction.getStatus());
        Transaction updatedtransaction = transactionRepository.save(transactionData);
        return new ResponseEntity<>(updatedtransaction, HttpStatus.OK);
    }

    @PostMapping("/transaction/List")
    public Transaction createTransaction(@RequestBody Transaction transaction) {
        transactionRepository.save(transaction);
        Employee employeeData = employeeRepository.findByEmail(transaction.getEmail());
        Transaction transactionData = transactionRepository.findById(transaction.getId());
        Inventory inventoryData2= inventoryRepository.findByInventoryId(transaction.getTranscData().get(0).getInventoryId());
        List<TransData> transactions=transactionData.getTranscData();
        List<EmployeeItems> empItems = employeeData.getEmplItems();
        List<InventoryUsers> invenUsers=inventoryData2.getInvenUsers();

        for (int i=0;i<transactions.size();i++) {
            Inventory inventoryData = inventoryRepository.findByInventoryId(transactions.get(i).getInventoryId());
            if(inventoryData.getAvailable()<transactions.get(i).getQty()){
                transaction.setStatus("FAILED. ITEM/s NOT AVAILABLE");
                transactionRepository.save(transaction);
            }
            else
                {
            inventoryData.setAvailable(inventoryData.getAvailable()-transactions.get(i).getQty());
            System.out.println(inventoryData.getAvailable());
            inventoryRepository.save(inventoryData);}
        }
        if(employeeData.getRole().equals("MANAGER")){
            transaction.setStatus("APPROVED");
            transactionRepository.save(transaction);

            for (int i=0;i<transactions.size();i++){
                if (transactions.get(i).getInventoryId().equals(employeeData.getEmplItems().get(i).getInventoryId())){
                    empItems.get(i).setQty(empItems.get(i).getQty()+transactions.get(i).getQty());
                    employeeRepository.save(employeeData);
                    inventoryRepository.save(inventoryData2);
                    transactionRepository.save(transaction);
                }
                else{
                EmployeeItems a = new EmployeeItems();
                InventoryUsers b = new InventoryUsers();
                a.setQty(transactions.get(i).getQty());
                a.setInventoryId(transactions.get(i).getInventoryId());
                b.setEmail(transaction.getEmail());
                b.setQty(transactions.get(i).getQty());
                invenUsers.add(b);
//                empItems.get(i).setInventoryId(transactions.get(i).getInventoryId());
                empItems.add(a);
                employeeRepository.save(employeeData);
                inventoryRepository.save(inventoryData2);
                transactionRepository.save(transaction);
                }
            }
        }
        else if (employeeData.getRole().equals("ADMIN")){
            transaction.setStatus("PENDING");
            transactionRepository.save(transaction);
        }
        else if (employeeData.getRole().equals("USER")){
            transaction.setStatus("PENDING");
            transactionRepository.save(transaction);
        }
        else{
            transaction.setStatus("ROLE ???");
            transactionRepository.save(transaction);
        }
        return transactionRepository.save(transaction);
    }

    @PostMapping(value = "/transaction/assignment", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse authenticate(@RequestBody TransactionRequest request) {
        TransactionResponse t = new TransactionResponse();
        Employee employeeData = employeeRepository.findByEmail(request.getEmail());
        Inventory inventoryData = inventoryRepository.findByInventoryId(request.getInventoryId());
        Transaction transactionData = transactionRepository.findById(request.getId());
        List<TransData> transaction = transactionData.getTranscData();
        List<EmployeeItems> empItems = employeeData.getEmplItems();

        if (transaction == null) {
            t.setSuccess("Transaction NULL");
            return t;
        }
        if(request.getStatus().equals("REJECTED")){
            if (employeeData.getEmail().equals(request.getEmail())){
                for (int i = 0; i < transaction.size(); i++) {
                    if (transaction.get(i).getInventoryId().equals(inventoryData.getInventoryId())) {
                            inventoryData.setAvailable(inventoryData.getAvailable() + transaction.get(i).getQty());
                            inventoryRepository.save(inventoryData);
                            t.setSuccess("Transaction REJECTED");
                            return t; }
                    else {
                        t.setSuccess("ITEM/s NOT FOUND");
                        return t; }
                }
            }
            else {
                t.setSuccess("Employee Not Found");
                return t;
            }
        }
        t.setSuccess("Transaction FAILED");
        return t;
    }


    @PostMapping(value = "/transaction/return", produces = MediaType.APPLICATION_JSON_VALUE)
    public TransactionResponse returned(@RequestBody TransactionRequest request) {
        TransactionResponse t = new TransactionResponse();
        Employee employeeData = employeeRepository.findByEmail(request.getEmail());
        Inventory inventoryData = inventoryRepository.findByInventoryId(request.getInventoryId());
        Transaction transactionData = transactionRepository.findById(request.getId());
        List<TransData> transaction = transactionData.getTranscData();
            if (employeeData.getEmail().equals(request.getEmail())){
                for (int i = 0; i < transaction.size(); i++) {
                    if (transaction.get(i).getInventoryId().equals(inventoryData.getInventoryId())) {
                        inventoryData.setAvailable(inventoryData.getAvailable() + transaction.get(i).getQty());
                        inventoryRepository.save(inventoryData);
                        t.setSuccess("Transaction RETURNED");

                        Update updateObj = new Update()
                                .pull("emplItems", new BasicDBObject("emplItems.inventoryId",request.getInventoryId()));

                        System.out.println("UPDATE OBJ: " + updateObj.toString());
                        return t; }
                    else {
                        t.setSuccess("ITEM/s NOT FOUND");
                        return t; }
                }
            }
            else {
                t.setSuccess("Employee Not Found");
                return t;
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