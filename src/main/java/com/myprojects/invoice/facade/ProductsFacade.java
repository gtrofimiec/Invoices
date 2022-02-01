package com.myprojects.invoice.facade;

import com.myprojects.invoice.domain.Products;
import com.myprojects.invoice.domain.dtos.ProductsDto;
import com.myprojects.invoice.exceptions.ProductAlreadyExistsException;
import com.myprojects.invoice.exceptions.ProductNotFoundException;
import com.myprojects.invoice.mappers.ProductsMapper;
import com.myprojects.invoice.services.ProductsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductsFacade {

    private final ProductsMapper productsMapper;
    private final ProductsService productsService;

    public List<ProductsDto> getProducts() {
        return productsMapper.mapToProductsDtoList(productsService.getAll());
    }

    public ProductsDto getProduct(Long productId) throws ProductNotFoundException {
        return productsMapper.mapToProductDto(productsService.getOne(productId));
    }

    public ProductsDto saveProduct(ProductsDto productsDto) throws ProductNotFoundException,
            ProductAlreadyExistsException {
        Products newProduct = productsMapper.mapToProduct(productsDto);
        productsService.save(newProduct);
        return productsMapper.mapToProductDto(newProduct);
    }

    public ProductsDto updateProduct(Long productId, ProductsDto productsDto)
            throws ProductNotFoundException {
        Products updatedProduct = productsMapper.mapToProduct(productsDto);
        updatedProduct.setId(productId);
        productsService.update(updatedProduct);
        return productsMapper.mapToProductDto(updatedProduct);
    }

    public void deleteProduct(Long productId) throws ProductNotFoundException {
        productsService.delete(productId);
    }
}