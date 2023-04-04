package com.example.productapi.service.implement;

import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.productapi.model.Product;
import com.example.productapi.repository.ProductRespository;
import com.example.productapi.service.DTO.ProductDTO;
import com.example.productapi.service.ProductService;
import com.example.productapi.service.mapper.ProductMapper;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductServiceImplement implements ProductService {

    private final ProductRespository repository;

    private final ProductMapper mapper = Mappers.getMapper(ProductMapper.class);


    public ProductServiceImplement(ProductRespository repository) {
        this.repository = repository;
    }

    @Override
    public ProductDTO create(ProductDTO dto) {
        Product entity = mapper.fromDto(dto);
        return mapper.toDto(repository.save(entity));
    }

    @Override
    public ProductDTO update(ProductDTO dto) throws Exception {
        Optional<Product> entity = repository.findById(dto.getId());
        Product saved;
        if (entity.isPresent()) {
             saved =  repository.save(mapper.merge(dto, entity.get()));
        } else {
            throw new Exception("Error on save");
        }
        return mapper.toDto(saved);
    }

    @Override
    public ProductDTO delete(Long  id) throws Exception {
        Optional<Product> entity = repository.findById(id);

        if (entity.isPresent()) {
            repository.deleteById(id);
        } else {
            throw new Exception("Error on deleting");
        }
        return mapper.toDto(entity.get());
    }

    @Override
    public Page<ProductDTO> findBy(String nombre, String description, BigDecimal precio,
                                   String categoria,
                                   Pageable page) {
        return mapper.toPage(repository.query(nombre,categoria,description, precio,page));
    }
}
