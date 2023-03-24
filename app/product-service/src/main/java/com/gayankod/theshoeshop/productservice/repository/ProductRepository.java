package com.gayankod.theshoeshop.productservice.repository;

import com.gayankod.theshoeshop.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
