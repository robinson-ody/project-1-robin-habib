package com.future.service;

import com.future.model.Inventory;
import com.future.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service("InventoryService")
public class InventoryService {
    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getAllInventory() {
        return (List<Inventory>) inventoryRepository.findAll();
    }

    public Inventory getUserInventory(@PathVariable("id") String id) {
        return inventoryRepository.findOne(id);}

        public Inventory createInventory(@RequestBody Inventory inventory) {
        inventoryRepository.save(inventory);
        inventory.setAvailable(inventory.getStock());
        return inventoryRepository.save(inventory);
    }

    public ResponseEntity<String> deleteInventory(@PathVariable("id") String id) {
        inventoryRepository.delete(id);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
