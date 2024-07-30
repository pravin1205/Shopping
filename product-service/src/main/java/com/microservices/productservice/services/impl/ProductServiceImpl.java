package com.microservices.productservice.services.impl;

import com.microservices.datamodels.dto.ProductRequest;
import com.microservices.datamodels.dto.ProductResponse;
import com.microservices.datamodels.model.Product;
import com.microservices.productservice.respository.ProductRepository;
import com.microservices.productservice.services.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    @Override
    public void createProduct(ProductRequest productRequest){
        Product product=modelMapper.map(productRequest, Product.class);
        productRepository.save(product);
        log.info("Product {} is saved",product.toString());
    }
    @Override
    public List<ProductResponse> getAllProducts(){
        List<Product> products=productRepository.findAll();
        return products.stream().map(product -> modelMapper.map(product, ProductResponse.class)).toList();
    }
}
