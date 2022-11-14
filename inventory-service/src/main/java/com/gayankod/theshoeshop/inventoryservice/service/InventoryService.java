package com.gayankod.theshoeshop.inventoryservice.service;

import com.gayankod.theshoeshop.inventoryservice.dto.InventoryResponse;
import com.gayankod.theshoeshop.inventoryservice.model.Inventory;
import com.gayankod.theshoeshop.inventoryservice.repository.InventoryRepository;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public void createInventory(Inventory inventory) {
        Inventory inventorydb = new Inventory();
        inventorydb.setSkuCode(inventory.getSkuCode());
        inventorydb.setQty(inventory.getQty());
        inventoryRepository.save(inventorydb);
        log.info("Inventory {} is saved", inventorydb.getId());
    }

    @Transactional(readOnly = true)
    public  List<InventoryResponse> isInStock(List<String> skuCode){

        return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                .map(inventory ->
                    InventoryResponse.builder().skuCode(inventory.getSkuCode())
                            .isInStock(inventory.getQty() > 0)
                            .build()
                ).toList();

    }
}
