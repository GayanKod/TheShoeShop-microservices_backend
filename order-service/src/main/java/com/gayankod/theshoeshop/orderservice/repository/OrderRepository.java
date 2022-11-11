package com.gayankod.theshoeshop.orderservice.repository;

import com.gayankod.theshoeshop.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {


}
