package com.myprojects.invoice.repositories;

import com.myprojects.invoice.domain.Products;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductsRepository extends CrudRepository<Products, Long> {

    @Override
    List<Products> findAll();

    @Override
    Optional<Products> findById(Long productId);

    @Override
    Products save(Products product);

    @Override
    void deleteById(Long productId);
}