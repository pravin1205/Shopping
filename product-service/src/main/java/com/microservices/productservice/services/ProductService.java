package com.microservices.productservice.services;

import com.microservices.datamodels.dto.ProductRequest;
import com.microservices.datamodels.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
