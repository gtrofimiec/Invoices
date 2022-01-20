package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.Products;
import com.myprojects.invoice.exceptions.ProductAlreadyExistsException;
import com.myprojects.invoice.exceptions.ProductNotFoundException;
import com.myprojects.invoice.repositories.ProductsRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class ProductsService {

    private final ProductsRepository productsRepository;

    public List<Products> getAll() {
        return productsRepository.findAll().stream()
                .filter(p -> !p.isDeleted())
                .collect(Collectors.toList());
    }

    public Products getOne(final Long id) throws ProductNotFoundException {
        return productsRepository.findById(id)
                .filter(p -> !p.isDeleted())
                .orElseThrow(ProductNotFoundException::new);
    }

    public Products save(final @NotNull Products product) throws ProductAlreadyExistsException {
        Long id = product.getId();
        if (id != null && productsRepository.existsById(id)) {
            throw new ProductAlreadyExistsException();
        }
        return productsRepository.save(product);
    }

    public Products update(final @NotNull Products product) throws ProductNotFoundException {
        Long id = product.getId();
        if (id == null || !productsRepository.existsById(id)) {
            throw new ProductNotFoundException();
        }
        return productsRepository.save(product);
    }

    public void delete(final Long id) throws ProductNotFoundException {
        Products product = productsRepository.findById(id)
                .orElseThrow(ProductNotFoundException::new);
        product.setDeleted(true);
        productsRepository.save(product);
    }
}