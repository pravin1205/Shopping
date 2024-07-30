package com.microservices.inventoryservice.repository;

import com.microservices.datamodels.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface InventoryRepository extends MongoRepository<Inventory,String> {
    List<Inventory> findBySkuCodeIn(List<String> skuCodes);
}
