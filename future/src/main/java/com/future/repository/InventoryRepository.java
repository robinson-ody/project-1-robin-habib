package com.future.repository;

import com.future.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

//import javax.persistence.criteria.CriteriaBuilder;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
    Inventory findByInventoryId(String inventoryId);
}
