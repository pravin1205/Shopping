package com.microservices.orderservice.controller;

import com.microservices.datamodels.dto.OrderRequest;
import com.microservices.orderservice.services.OrderService;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/placeOrder")
    @ResponseStatus(HttpStatus.CREATED)
//    @Retry(name = "inventory",fallbackMethod = "fallBackMethodInventory")
    @CircuitBreaker(name = "inventory",fallbackMethod = "fallBackMethodInventory")
    public String placeOrder(@RequestBody OrderRequest orderRequest){
        orderService.placeOrder(orderRequest);
        return "order placed";
    }
    public String fallBackMethodInventory(OrderRequest orderRequest,Exception exception){
        return "Sorry for the inconvenience......try again later";
    }
}
