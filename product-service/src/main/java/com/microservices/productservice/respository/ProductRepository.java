package com.microservices.productservice.respository;

import com.microservices.datamodels.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product,String> {
}
