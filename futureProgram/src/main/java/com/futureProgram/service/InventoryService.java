package com.futureProgram.service;

import com.futureProgram.model.Inventory;

import java.util.List;

public interface InventoryService {
    List<Inventory> findAll();
    List<Inventory> findByDetail(String detail);
    List<Inventory> findByInventoryId(String inventoryId);
    List<Inventory> findByDetailAndInventoryId(String detail, String inventoryId);
    void addOrUpdateInventory(Inventory inventory);
    void deleteInventory(String id);
}
