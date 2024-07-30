package com.microservices.apigateway.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/inventory-fallback")
public class FallBackController {

    @GetMapping
    public ResponseEntity<String> fallback() {
        return ResponseEntity.ok("Inventory service is currently unavailable. Please try again later.");
    }
}