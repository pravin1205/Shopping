package com.microservices.orderservice.feign;

import com.microservices.datamodels.dto.InventoryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "INVENTORY-SERVICE", path = "/api/inventory")
public interface InventoryService {
    @GetMapping("/getStock")
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes);
}
