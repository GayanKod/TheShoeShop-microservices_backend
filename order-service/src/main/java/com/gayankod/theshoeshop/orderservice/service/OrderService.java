package com.gayankod.theshoeshop.orderservice.service;

import com.gayankod.theshoeshop.orderservice.dto.OrderLineItemsDTO;
import com.gayankod.theshoeshop.orderservice.dto.OrderRequest;
import com.gayankod.theshoeshop.orderservice.model.Order;
import com.gayankod.theshoeshop.orderservice.model.OrderLineItems;
import com.gayankod.theshoeshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(orderLineItemsDTO -> mapToDTO(orderLineItemsDTO)).toList();

        order.setOrderLineItemsList(orderLineItems);

//        Calling Inventory Service to check the product is in stock and place the order
        Boolean callInventoryResult = webClient.get()
                .uri("http://localhost:8082/api/inventory")
                        .retrieve()
                                .bodyToMono(Boolean.class)
                                        .block();

        if(callInventoryResult){
            orderRepository.save(order);
        }else {
            throw new IllegalArgumentException("Product is out of stock");
        }

        orderRepository.save(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQty(orderLineItemsDTO.getQty());
        return orderLineItems;
    }
}
