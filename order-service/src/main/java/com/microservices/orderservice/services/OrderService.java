package com.microservices.orderservice.services;

import com.microservices.datamodels.dto.OrderRequest;

public interface OrderService {
    void placeOrder(OrderRequest orderRequest);
}
