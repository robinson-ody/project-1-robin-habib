package com.future.controller;

import com.future.model.Image;
import com.future.model.Inventory;
import com.future.repository.EmployeeRepository;
import com.future.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping("/inventory")
    public List<Inventory> getAllInventory() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    @GetMapping("/inventory/{id}")
    public Inventory getUserInventory(@PathVariable("id") String id) {

        return (Inventory) inventoryRepository.findOne(id);
    }

    @PostMapping("/inventory/create")
    public Inventory createInventory(@RequestBody Inventory inventory) {
        inventory.setAvailable(inventory.getStock());
        return inventoryRepository.save(inventory);
    }

/*    @PostMapping("/inventory/create")
    public Inventory createInventory(@ModelAttribute Image inventory) {
        inventory.setAvailable(inventory.getStock());
        Inventory inventoryData = inventoryRepository.findByInventoryId(inventory.getInventoryId());
        return inventoryRepository.save(inventory);
    }*/

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