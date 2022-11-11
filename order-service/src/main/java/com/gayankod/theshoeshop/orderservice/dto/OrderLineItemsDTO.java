package com.gayankod.theshoeshop.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDTO {
    private Long id;
    private BigDecimal price;
    private Integer qty;
}
