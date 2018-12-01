package com.futureProgram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.futureProgram.model.Inventory;
import com.futureProgram.repository.InventoryRepository;

import java.util.List;

@Service
public class InventoryServiceImpl implements InventoryService{

    @Autowired
    InventoryRepository inventoryRepository;

    @Override
    public List<Inventory>findAll(){
        return inventoryRepository.findAll();
    }

    @Override
    public List<Inventory> findByDetail(String detail) {
        return inventoryRepository.findByDetail(detail);
    }

    @Override
    public List<Inventory> findByInventoryId(String inventoryId) {
        return inventoryRepository.findByInventoryId(inventoryId);
    }

    @Override
    public List<Inventory> findByDetailAndInventoryId(String detail,String inventoryId) {
        return inventoryRepository.findByDetailAndInventoryId(detail,inventoryId);
    }

    @Override
    public void addOrUpdateInventory(Inventory inventory) {
        inventoryRepository.save(inventory);
    }

    @Override
    public void deleteInventory(String id) {
        inventoryRepository.delete(id);
    }

}
