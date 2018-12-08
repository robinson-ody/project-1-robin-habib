package com.future.repository;

import com.future.model.Inventory;
import org.springframework.data.domain.Slice;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends MongoRepository<Inventory, String> {
/*    @Override
    Inventory findOne(String id);

    @Override
    void delete(Inventory deleted);*/

}
