package com.gayankod.theshoeshop.inventoryservice.controller;

import com.gayankod.theshoeshop.inventoryservice.dto.InventoryResponse;
import com.gayankod.theshoeshop.inventoryservice.model.Inventory;
import com.gayankod.theshoeshop.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    // http://localhost:8082/api/inventory?skuCode=Nike_W&skuCode=Nike_Blk

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestParam List<String> skuCode){
        return inventoryService.isInStock(skuCode);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createInventory(@RequestBody Inventory inventory){
        inventoryService.createInventory(inventory);
        return "Inventory created Successfully";
    }

}
