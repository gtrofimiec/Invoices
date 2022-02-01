package com.myprojects.invoice.domain;

import com.myprojects.invoice.repositories.ProductsRepository;
import com.myprojects.invoice.services.ProductsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductsTestSuite {

    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductsService productsService;

    @Test
    public void shouldFindAllProducts() {

        // Given
        long currentNumberOfProducts = productsRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Products product1 = new Products();
        Products product2 = new Products();

        // When
        productsRepository.save(product1);
        productsRepository.save(product2);
        long availableProducts = productsRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();

        // Then
        assertEquals(currentNumberOfProducts + 2, availableProducts);

        // Clean Up
        productsRepository.deleteById(product1.getId());
        productsRepository.deleteById(product2.getId());
    }

    @Test
    public void shouldFindProductById() {

        // Given
        Products product1 = new Products();
        Products product2 = new Products();

        // When
        productsRepository.save(product1);
        productsRepository.save(product2);
        Long product1Id = product1.getId();
        Optional<Products> foundProduct = productsRepository.findById(product1Id);

        // Then
        assertNotNull(foundProduct);
        assertEquals(product1Id, foundProduct.get().getId());

        // Clean Up
        productsRepository.deleteById(product1Id);
        productsRepository.deleteById(product2.getId());
    }

    @Test
    public void shouldSaveProduct() {

        // Given
        Products product1 = new Products();
        Products product2 = new Products();
        product1.setName("Product1");
        product2.setName("Product2");

        // When
        productsRepository.save(product1);
        productsRepository.save(product2);
        Long product1Id = product1.getId();
        Long product2Id = product2.getId();
        Optional<Products> savedProduct1 = productsRepository.findById(product1Id);
        Optional<Products> savedProduct2 = productsRepository.findById(product2Id);
        String name1 = savedProduct1.get().getName();
        String name2 = savedProduct2.get().getName();

        // Then
        assertEquals("Product1", name1);
        assertEquals("Product2", name2);

        // Clean Up
        productsRepository.deleteById(product1Id);
        productsRepository.deleteById(product2Id);
    }

    @Test
    public void shouldUpdateProduct() {

        // Given
        Products product = new Products();

        // When
        productsService.save(product);
        product.setName("Product1");
        productsService.update(product);
        Long productId = product.getId();
        Products actualProduct = productsService.getOne(productId);

        // Then
        assertTrue(productsRepository.existsById(productId));
        assertEquals("Product1", actualProduct.getName());

        // Clean Up
        productsRepository.deleteById(productId);
    }

    @Test
    public void shouldDeleteProductById() {

        // Given
        long currentNumberOfProducts = productsRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Products product1 = new Products();
        Products product2 = new Products();

        // When
        productsRepository.save(product1);
        productsRepository.save(product2);
        Long product1Id = product1.getId();
        productsRepository.deleteById(product1Id);
        Optional<Products> removedProduct = productsRepository.findById(product1Id);
        long availableProducts = productsRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();

        // Then
        assertEquals(Optional.empty(), removedProduct);
        assertEquals( currentNumberOfProducts + 1, availableProducts);

        // Clean Up
        productsRepository.deleteById(product2.getId());
    }
}