package com.example.productapi.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.productapi.service.DTO.ProductDTO;
import com.example.productapi.service.ProductService;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@Validated
@RequestMapping(value = "/product-api")
public class ProductController {

    private final ProductService service;

    @PostMapping
    public ProductDTO create(@Valid @RequestBody ProductDTO client) {
        return service.create(client);
    }

    @PutMapping
    public ProductDTO update(@Valid @RequestBody ProductDTO client) throws Exception {
        return service.update(client);
    }

    @GetMapping
    public Page<ProductDTO> List(
        @RequestParam(required = false) String nombre,
        @RequestParam(required = false) String description,
        @RequestParam(required = false) BigDecimal precio,
        @RequestParam(required = false) String categoria, Pageable page) {
        return service.findBy(nombre, description, precio, categoria, page);
    }

    @DeleteMapping("{id}")
    public ProductDTO delete(@PathVariable(value = "id") Long productId) throws Exception {
        return service.delete(productId);
    }

}
