package com.gayankod.theshoeshop.productservice.controller;

import com.gayankod.theshoeshop.productservice.dto.ProductRequest;
import com.gayankod.theshoeshop.productservice.dto.ProductResponse;
import com.gayankod.theshoeshop.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createProduct(@RequestBody ProductRequest productRequest){
        productService.createProduct(productRequest);
        return "Product Created Successfully!";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts(){
        return productService.getAllProducts();
    }
}
