package com.microservices.inventoryservice.service.impl;

import com.microservices.datamodels.dto.InventoryResponse;
import com.microservices.datamodels.model.Inventory;
import com.microservices.inventoryservice.repository.InventoryRepository;
import com.microservices.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        List<Inventory> inventoryList = inventoryRepository.findBySkuCodeIn(skuCodes);
        return inventoryList.stream().map(inventory ->
                InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity()>0)
                        .build()
        ).toList();
    }
}
