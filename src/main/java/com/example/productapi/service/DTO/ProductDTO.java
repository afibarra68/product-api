package com.example.productapi.service.DTO;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true, callSuper = false)
public class ProductDTO {

    private Long id;

    @NonNull
    private String nombre;

    @NonNull
    private String description;

    @NonNull
    private BigDecimal precio;

    @NonNull
    private String categoria;

}
