package com.futureProgram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.futureProgram.model.Inventory;
import com.futureProgram.service.InventoryService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<?>getAll(){
        List<Inventory>result=inventoryService.findAll();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping ("/{detail}/{inventoryId}")
    public ResponseEntity<?> getByDetailAndInventoryId(@PathVariable("detail") String detail,@PathVariable("inventoryId") String inventoryId) {
        List<Inventory> result = new ArrayList<>();
        if("All".equals(inventoryId)){
            result = inventoryService.findByInventoryId(inventoryId);
        }
        else if("All".equals(detail)){
            result = inventoryService.findByDetail(detail);
        }
        else {
            result = inventoryService.findByDetailAndInventoryId(detail,inventoryId);
        }
            return new ResponseEntity(result, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addOrUpdateInventory(@RequestBody Inventory inventory) {
        inventoryService.addOrUpdateInventory(inventory);
        return new ResponseEntity("Inventory added succcessfully", HttpStatus.OK);
    }

    @DeleteMapping
    public void deleteInventory(@RequestParam("id") String id) {
        inventoryService.deleteInventory(id);
    }

}
