package com.microservices.datamodels.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
@Data
@Document("order_line_items")
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    private String id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}
