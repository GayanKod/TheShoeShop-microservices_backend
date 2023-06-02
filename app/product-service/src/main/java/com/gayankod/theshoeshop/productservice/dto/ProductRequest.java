package com.gayankod.theshoeshop.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {
    private String title;
    private String src;
    private String description;
    private String content;
    private BigDecimal price;
    private List<String> colors;
    private Integer count;
    private String skuCode;
}
