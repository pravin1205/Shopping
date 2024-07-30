package com.microservices.orderservice.services.impl;

import com.microservices.datamodels.dto.InventoryResponse;
import com.microservices.datamodels.dto.OrderRequest;
import com.microservices.datamodels.model.Order;
import com.microservices.datamodels.model.OrderLineItems;
import com.microservices.orderservice.feign.InventoryService;
import com.microservices.orderservice.repository.OrderRepository;
import com.microservices.orderservice.services.OrderService;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
@Service
@RequiredArgsConstructor
@Transactional
public class OrderServiceImpl implements OrderService {
    private final InventoryService inventoryService;
    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    @Autowired
    private ModelMapper mapper;
    @Override
    public void placeOrder(OrderRequest orderRequest){
        Order order=new Order();

        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().stream()
                .map(orderLineItemsDto -> mapper.map(orderLineItemsDto, OrderLineItems.class))
                .toList();
        order.setOrderLineItemsList(orderLineItemsList);

        List<String> skuCodes = orderLineItemsList.stream().map(OrderLineItems::getSkuCode).toList();

//        InventoryResponse[] inventoryResponses = webClientCall(skuCodes);
        List<InventoryResponse> inventoryResponses = feignClientCall(skuCodes);
//        boolean match = Arrays.stream(inventoryResponses).allMatch(InventoryResponse::isInStock);
        boolean match = inventoryResponses.stream().allMatch(InventoryResponse::isInStock);
        if (match){
            orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("Product not available in stock");
        }
    }
//    @HystrixCommand(fallbackMethod = "fallbackMethod" )

//@Retry(name = "inventory", fallbackMethod = "fallBackMethod")

public InventoryResponse[] webClientCall(List<String> skuCodes){
        return webClientBuilder.build().get()
                .uri("http://inventory-service/api/inventory/getStock",
                        uriBuilder -> uriBuilder.queryParam("skuCodes", skuCodes).build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();
    }
//    @CircuitBreaker(name = "inventory", fallbackMethod = "fallBackMethodInventory")
    public List<InventoryResponse> feignClientCall(List<String> skuCodes){
        return inventoryService.isInStock(skuCodes);
    }
//    public List<InventoryResponse> fallBackMethodInventory(List<String> skuCodes,Throwable throwable){
//        return new ArrayList<>();
//    }
}
