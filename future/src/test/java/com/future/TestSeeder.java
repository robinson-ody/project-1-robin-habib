/*
package com.future;

import com.future.model.Inventory;
import com.future.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestSeeder {

    @Autowired
    private InventoryRepository inventoryRepository;

    public void cleanUp() {
        inventoryRepository.deleteAll();
    }

    public void createProducts() {
        final Inventory inventory = new Inventory();
        inventory.setInventoryId("TestInven-123");
        inventory.setDetail("Test Inventory");
        inventory.setStock(99);
        inventory.setPrice(123456);
        inventoryRepository.save(inventory);

        final Inventory inventory2 = new Inventory();
        inventory.setInventoryId("TestInven-999");
        inventory.setDetail("Test Inventory Dua");
        inventory.setStock(1);
        inventory.setPrice(999999);
        inventoryRepository.save(inventory2);
    }
}*/
