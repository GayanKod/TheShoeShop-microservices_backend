package com.gayankod.theshoeshop.orderservice.service;

import com.gayankod.theshoeshop.orderservice.dto.InventoryResponse;
import com.gayankod.theshoeshop.orderservice.dto.OrderLineItemsDTO;
import com.gayankod.theshoeshop.orderservice.dto.OrderRequest;
import com.gayankod.theshoeshop.orderservice.event.OrderPlacedEvent;
import com.gayankod.theshoeshop.orderservice.model.Order;
import com.gayankod.theshoeshop.orderservice.model.OrderLineItems;
import com.gayankod.theshoeshop.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClientBuilder;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDTOList()
                .stream()
                .map(orderLineItemsDTO -> mapToDTO(orderLineItemsDTO)).toList();

        order.setOrderLineItemsList(orderLineItems);

        List<String> skuCodes = order.getOrderLineItemsList().stream().map(OrderLineItems::getSkuCode).toList();

//        Calling Inventory Service to check the product is in stock and place the order
        InventoryResponse[] callInventoryResults = webClientBuilder.build().get()
                .uri("http://inventory-service.default.svc.cluster.local/api/inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block();

        boolean isProductsInStock = Arrays.stream(callInventoryResults).allMatch(inventoryResponse -> inventoryResponse.isInStock());

        if(isProductsInStock){
            orderRepository.save(order);
            kafkaTemplate.send("notification", new OrderPlacedEvent(order.getOrderNumber()));
            return "Order placed Successfully";
        }else {
            return "Products are out of stock";
        }
    }

    private OrderLineItems mapToDTO(OrderLineItemsDTO orderLineItemsDTO) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setSkuCode(orderLineItemsDTO.getSkuCode());
        orderLineItems.setPrice(orderLineItemsDTO.getPrice());
        orderLineItems.setQty(orderLineItemsDTO.getQty());
        return orderLineItems;
    }
}
