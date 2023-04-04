package com.example.productapi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.productapi.model.Product;
import java.math.BigDecimal;

public interface ProductRespository  extends JpaRepository<Product, Long> {

    @Query("SELECT p "
        + "FROM Product p "
        + "WHERE (:nombre IS NULL OR p.nombre = :nombre) "
        + " AND (:categoria IS NULL OR p.categoria = :categoria)"
        + " AND (:description IS NULL OR p.description = :description)"
        + " AND (:precio IS NULL OR p.precio = :precio)"
    )
    Page<Product> query(
        @Param("nombre") String nombre,
        @Param("categoria") String categoria,
        @Param("description") String description,
        @Param("precio") BigDecimal precio,
        Pageable page);


}
