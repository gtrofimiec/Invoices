package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.ProductsDto;
import com.myprojects.invoice.exceptions.ProductAlreadyExistsException;
import com.myprojects.invoice.exceptions.ProductNotFoundException;
import com.myprojects.invoice.facade.ProductsFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("v1/invoices")
public class ProductsController {

    private final ProductsFacade productsFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/products")
    public List<ProductsDto> getProducts() {
        return productsFacade.getProducts();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/products/{id}")
    public ProductsDto getProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        return productsFacade.getProduct(productId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products",
            consumes = APPLICATION_JSON_VALUE)
    public ProductsDto createProduct(@RequestBody ProductsDto productsDto) throws ProductNotFoundException,
            ProductAlreadyExistsException {
        return productsFacade.saveProduct(productsDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/products/{id}")
    public ProductsDto updateProduct(@PathVariable("id") Long productId, @RequestBody ProductsDto productsDto)
            throws ProductNotFoundException {
        return productsFacade.updateProduct(productId, productsDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/products/{id}")
    public void deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundException {
        productsFacade.deleteProduct(productId);
    }
}