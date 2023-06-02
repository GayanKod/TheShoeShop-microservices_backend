package com.gayankod.theshoeshop.productservice.service;

import com.gayankod.theshoeshop.productservice.dto.ProductRequest;
import com.gayankod.theshoeshop.productservice.dto.ProductResponse;
import com.gayankod.theshoeshop.productservice.model.Product;
import com.gayankod.theshoeshop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .title(productRequest.getTitle())
                .src(productRequest.getSrc())
                .description(productRequest.getDescription())
                .content(productRequest.getContent())
                .price(productRequest.getPrice())
                .colors(productRequest.getColors())
                .count(productRequest.getCount())
                .skuCode(productRequest.getSkuCode())
                .build();

        productRepository.save(product);
        log.info("Product {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(product -> mapToProductResponse(product)).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .title(product.getTitle())
                .src(product.getSrc())
                .description(product.getDescription())
                .content(product.getContent())
                .price(product.getPrice())
                .colors(product.getColors())
                .count(product.getCount())
                .skuCode(product.getSkuCode())
                .build();

    }
}
