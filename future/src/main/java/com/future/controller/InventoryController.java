package com.future.controller;

import com.future.exception.ResourceNotFoundException;
import com.future.model.Inventory;
import com.future.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InventoryController {

/*    @Transient
    public static final String SEQUENCE_NAME = "inventory_sequence";*/

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public List<Inventory> getAllInventory() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    @PostMapping("/inventory/create")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @PutMapping("/inventory/{id}")
    public ResponseEntity<Inventory> updateInventory(@PathVariable("id") String id, @RequestBody Inventory inventory) {
        Inventory inventoryData = inventoryRepository.findOne(id);
        if (inventory == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        inventoryData.setInventoryId(inventory.getInventoryId());
        inventoryData.setDetail(inventory.getDetail());
        inventoryData.setStock(inventory.getStock());
        inventoryData.setPrice(inventory.getPrice());
        Inventory updatedinventory = inventoryRepository.save(inventoryData);
        return new ResponseEntity<>(updatedinventory, HttpStatus.OK);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") String id) {
        inventoryRepository.delete(id);
        return new ResponseEntity<>(id,HttpStatus.OK);
    }

}