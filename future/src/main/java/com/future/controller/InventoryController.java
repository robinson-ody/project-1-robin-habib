package com.future.controller;

import com.future.model.Inventory;
import com.future.repository.InventoryRepository;
import com.future.service.InventoryService;
import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping("/inventory")
    public List<Inventory> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/inventory/{id}")
    public Inventory getUserInventory(@PathVariable("id") String id) {
        return inventoryService.getUserInventory(id);
    }

    @PostMapping("/inventory/create")
    public Inventory createInventory(@RequestBody Inventory inventory) {return inventoryService.createInventory(inventory);
    }

    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<String> deleteInventory(@PathVariable("id") String id) {
        return inventoryService.deleteInventory(id);
    }
}
