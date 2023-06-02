package com.gayankod.theshoeshop.productservice;

import com.gayankod.theshoeshop.productservice.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;
import org.testcontainers.utility.DockerImageName;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {
	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:4.4.2");
	//Test will start with the MongoDB Container by downloading the mongo version 4.4.2 image
	//and then after starting the container it will set the replica url
	//and added spring.data.mongodb.uri property dynamically at the time of creating the test
	@Autowired
	private MockMvc mockMvc;
	ObjectMapper objMapper = new ObjectMapper();

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest proReq= getProductRequest();
		String proReqStr = objMapper.writeValueAsString(proReq);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(proReqStr)
		).andExpect(status().isCreated());
	}

	private ProductRequest getProductRequest(){
		return ProductRequest.builder()
				.title("Test Product")
				.description("Test Description")
				.price(BigDecimal.valueOf(1000))
				.build();
	}

}
