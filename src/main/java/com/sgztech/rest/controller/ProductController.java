package com.sgztech.rest.controller;

import com.sgztech.rest.dto.ProductDTO;
import com.sgztech.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@RequestBody @Valid ProductDTO product) {
        return service.save(product);
    }

    @GetMapping("{id}")
    public ProductDTO getById(@PathVariable Integer id) {
        return service.getById(id);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Integer id,
                       @RequestBody @Valid ProductDTO product) {
        service.update(id, product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        service.delete(id);
    }

    @GetMapping
    public List<ProductDTO> find(ProductDTO filter) {
        return service.find(filter);
    }
}
