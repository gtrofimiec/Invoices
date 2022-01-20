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
        Products product1 = new Products();
        Products product2 = new Products();

        // When
        productsRepository.save(product1);
        productsRepository.save(product2);

        // Then
        assertEquals(2, productsRepository.findAll().size());

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
        Long id = product1.getId();
        Optional<Products> foundProduct = productsRepository.findById(id);

        // Then
        assertNotNull(foundProduct);
        assertEquals(id, foundProduct.get().getId());

        // Clean Up
        productsRepository.deleteById(product1.getId());
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
        productsRepository.deleteById(product1.getId());
        productsRepository.deleteById(product2.getId());
    }

    @Test
    public void shouldUpdateProduct() {

        // Given
        Products product = new Products();
        Products updatedProduct = new Products();
        product.setName("Product1");
        updatedProduct.setName("Product2");

        // When
        productsService.save(product);
        Long id = product.getId();
        updatedProduct.setId(id);
        productsService.update(updatedProduct);
        Products actualProduct = productsService.getOne(id);

        // Then
        assertTrue(productsRepository.existsById(id));
        assertEquals(updatedProduct.getId(), actualProduct.getId());
        assertEquals(updatedProduct.getName(), actualProduct.getName());
        assertNotEquals(product.getName(), actualProduct.getName());

        // Clean Up
        productsRepository.deleteById(product.getId());
    }

    @Test
    public void shouldDeleteProductById() {

        // Given
        Products product1 = new Products();
        Products product2 = new Products();

        // When
        productsRepository.save(product1);
        productsRepository.save(product2);
        Long id = product1.getId();
        productsRepository.deleteById(id);
        Optional<Products> removedProduct = productsRepository.findById(id);
        int storedProducts = productsRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedProduct);
        assertEquals(1, storedProducts);

        // Clean Up
        productsRepository.deleteById(product2.getId());
    }
}