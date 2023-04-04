package com.example.productapi;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRespository;
import com.example.productapi.service.DTO.ProductDTO;
import com.example.productapi.service.implement.ProductServiceImplement;
import java.math.BigDecimal;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

class ProductApiApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	ProductServiceImplement implementTest;

	@Autowired
	ProductRespository repository;

	@Test
	void testCreate(){
		ProductDTO testCreate = ProductDTO.builder()
			.id(100L)
			.nombre("RELOJ_4990")
			.precio(new BigDecimal(10000))
			.description("reloj uno")
			.categoria("categoria 10")
			.build();

		ProductDTO result = implementTest.create(testCreate);

		Assertions.assertEquals(testCreate.getPrecio(), result.getPrecio());
		Assertions.assertEquals(testCreate.getCategoria(), result.getCategoria());
		Assertions.assertEquals(testCreate.getDescription(), result.getDescription());
	}

	@Test
	void testUpdate() throws Exception {
		ProductDTO testCreate = ProductDTO.builder()
			.nombre("RELOJ_4990")
			.precio(new BigDecimal(10000))
			.description("reloj uno")
			.categoria("categoria 10")
			.build();

		ProductDTO saved = implementTest.create(testCreate);

		BigDecimal newPrice = new BigDecimal(20000);

		saved.setPrecio(newPrice);

		ProductDTO result = implementTest.update(saved);
		Assertions.assertEquals(result.getCategoria(), result.getCategoria());
		Assertions.assertEquals(result.getPrecio(), newPrice);
	}

	@Test
	void testDelete() throws Exception {

		ProductDTO saved = implementTest.create(
			ProductDTO.builder()
			.nombre("RELOJ_4990")
			.precio(new BigDecimal(10000))
			.description("reloj uno")
			.categoria("categoria 10")
			.build());

		Optional<Product> productSaved =  repository.findById(saved.getId());

		Assertions.assertTrue(productSaved.isPresent());
		Assertions.assertEquals(saved.getId(), productSaved.get().getId());
		repository.delete(productSaved.get());
	}


}
