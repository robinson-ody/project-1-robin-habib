package com.futureProgram.repository;

import com.futureProgram.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    List<Inventory> findByDetailAndInventoryId(String detail,String inventoryId);
    List<Inventory> findByDetail(String detail);
    List<Inventory> findByInventoryId(String inventroryId);

    void delete(String id);
}
