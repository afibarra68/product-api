package com.example.productapi.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.productapi.service.DTO.ProductDTO;
import java.math.BigDecimal;

public interface ProductService {

    ProductDTO create(ProductDTO client);

    ProductDTO update(ProductDTO client) throws Exception;

    ProductDTO delete(Long clientId) throws Exception;

    Page<ProductDTO> findBy(String nombre, String description, BigDecimal precio,
                            String categoria,
                            Pageable page);
}
