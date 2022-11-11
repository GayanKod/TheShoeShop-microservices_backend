package com.gayankod.theshoeshop.orderservice.service;

import com.gayankod.theshoeshop.orderservice.dto.OrderLineItemsDTO;
import com.gayankod.theshoeshop.orderservice.dto.OrderRequest;
import com.gayankod.theshoeshop.orderservice.model.Order;
import com.gayankod.theshoeshop.orderservice.model.OrderLineItems;
import com.gayankod.theshoeshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(orderLineItemsDTO -> mapToDTO(orderLineItemsDTO)).toList();

        order.setOrderLineItemsList(orderLineItems);
        orderRepository.save(order);
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQty(orderLineItemsDTO.getQty());
        return orderLineItems;
    }
}
