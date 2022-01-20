package com.myprojects.invoice.mappers;

import com.myprojects.invoice.domain.Products;
import com.myprojects.invoice.domain.dtos.ProductsDto;
import com.myprojects.invoice.exceptions.ProductNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsMapper {

    public Products mapToProduct(final @NotNull ProductsDto productDto) throws ProductNotFoundException {
        Products product = new Products();
            product.setId(productDto.getId());
            product.setName(productDto.getName());
            product.setVatRate(productDto.getVatRate());
            product.setNetPrice(productDto.getNetPrice());
            product.setVatValue(productDto.getVatValue());
            product.setGrossPrice(productDto.getGrossPrice());
        return product;
    }

    public ProductsDto mapToProductDto(final @NotNull Products product) {
        ProductsDto productDto = new ProductsDto();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setVatRate(product.getVatRate());
            productDto.setNetPrice(product.getNetPrice());
            productDto.setVatValue(product.getVatValue());
            productDto.setGrossPrice(product.getGrossPrice());
        return productDto;
    }

    public List<ProductsDto> mapToProductsDtoList(final @NotNull List<Products> productsList) {
        return productsList.stream()
                .map(this::mapToProductDto)
                .collect(Collectors.toList());
    }

    public List<Products> mapToProductsList(final @NotNull List<ProductsDto> productsDtoList) {
        return productsDtoList.stream()
                .map(this::mapToProduct)
                .collect(Collectors.toList());
    }
}