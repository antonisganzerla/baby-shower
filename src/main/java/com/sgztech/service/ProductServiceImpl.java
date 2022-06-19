package com.sgztech.service;

import com.sgztech.domain.entity.Product;
import com.sgztech.domain.entity.User;
import com.sgztech.domain.repository.ProductRepository;
import com.sgztech.domain.repository.UserRepository;
import com.sgztech.exception.EntityNotFoundException;
import com.sgztech.rest.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ProductDTO save(ProductDTO dto) {
        userRepository
                .findById(dto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        Product product = repository.save(mapToProduct(dto));
        return mapToProductDTO(product);
    }

    @Override
    public ProductDTO getById(Integer id) {
        Product product = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
        return mapToProductDTO(product);
    }

    @Override
    public void update(Integer id, ProductDTO dto) {
        repository.findById(id)
                .map(p -> {
                    dto.setId(p.getId());
                    dto.setUserId(p.getUser().getId());
                    repository.save(mapToProduct(dto));
                    return Void.TYPE;
                }).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    @Override
    public void delete(Integer id) {
        repository.findById(id)
                .map(p -> {
                    repository.delete(p);
                    return Void.TYPE;
                }).orElseThrow(() -> new EntityNotFoundException("Produto não encontrado"));
    }

    @Override
    public List<ProductDTO> find(ProductDTO filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Product product = mapToProduct(filter);
        Example<Product> example = Example.of(product, matcher);
        return repository.findAll(example)
                .stream().map(p -> mapToProductDTO(p))
                .collect(Collectors.toList());
    }

    private Product mapToProduct(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        User user = new User();
        user.setId(dto.getUserId());
        product.setUser(user);
        return product;
    }

    private ProductDTO mapToProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setDescription(product.getDescription());
        dto.setPrice(product.getPrice());
        dto.setUserId(product.getUser().getId());
        return dto;
    }
}
