package com.microservices.productservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.datamodels.dto.ProductRequest;
import com.microservices.productservice.respository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
public class Main {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private MockMvc mockMvc;
    @Container
    static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo:4.4.2");
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry properties){
        properties.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
    }

    @Test
    void shouldCreateProduct() throws Exception {
        ProductRequest productRequest=getproductRequest();
        String productRequsestString= mapper.writeValueAsString(productRequest);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product/createProduct")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(productRequsestString))
                .andExpect(status().isCreated());
        Assertions.assertEquals(1, productRepository.findAll().size());
    }

    private ProductRequest getproductRequest() {
        return ProductRequest.builder().name("iphone 12")
                .description("good camera quality")
                .price(BigDecimal.valueOf(1150000))
                .build();
    }
    @Test
    void shouldGetAll() throws Exception {
        shouldCreateProduct();
        mockMvc.perform(MockMvcRequestBuilders.get("/api/product/getAllProducts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        Assertions.assertNotEquals(0,productRepository.findAll().size());
    }
}
