package com.microservices.inventoryservice.controller;

import com.microservices.datamodels.dto.InventoryResponse;
import com.microservices.inventoryservice.service.InventoryService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@Slf4j
public class InventoryController {
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private InventoryService inventoryService;
    @GetMapping("/getStock")
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCodes) throws InterruptedException {
        log.info(httpServletRequest.getRequestURL().toString());
//        Thread.sleep(10000);
        return inventoryService.isInStock(skuCodes);
    }
}
