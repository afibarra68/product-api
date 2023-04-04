package com.example.productapi.service.mapper;

import static org.mapstruct.ReportingPolicy.IGNORE;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.data.domain.Page;

import com.example.productapi.model.Product;
import com.example.productapi.service.DTO.ProductDTO;

@Mapper(unmappedTargetPolicy = IGNORE)
public interface ProductMapper {

    Product fromDto(ProductDTO dto);

    ProductDTO toDto(Product entity);

    Product merge(ProductDTO dto, @MappingTarget Product client);

    default Page<ProductDTO> toPage(Page<Product> page) {
        return page.map(this::toDto);
    }
}
