package com.gayankod.theshoeshop.inventoryservice;

import com.gayankod.theshoeshop.inventoryservice.model.Inventory;
import com.gayankod.theshoeshop.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("Nike White S");
			inventory.setQty(5);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("Nike Black S");
			inventory1.setQty(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}

}
