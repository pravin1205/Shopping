package com.microservices.inventoryservice.service;

import com.microservices.datamodels.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    List<InventoryResponse> isInStock(List<String> skuCodes);
}
