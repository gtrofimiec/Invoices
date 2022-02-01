package com.myprojects.invoice.domain;

import com.myprojects.invoice.exceptions.CustomerNotFoundException;
import com.myprojects.invoice.exceptions.ProductNotFoundException;
import com.myprojects.invoice.exceptions.UserNotFoundException;
import com.myprojects.invoice.repositories.CustomersRepository;
import com.myprojects.invoice.repositories.InvoicesRepository;
import com.myprojects.invoice.repositories.ProductsRepository;
import com.myprojects.invoice.repositories.UsersRepository;
import com.myprojects.invoice.services.InvoicesService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoicesTestSuite {

    @Autowired
    private InvoicesRepository invoicesRepository;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private InvoicesService invoicesService;

    @Test
    public void shouldFindAllInvoices() {

        // Given
        long currentNumberOfInvoices = invoicesRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Invoices invoice1 = new Invoices("1/2022", Date.from(Instant.now()), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");
        Invoices invoice2 = new Invoices("2/2022", Date.from(Instant.now()), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");

        // When
        invoicesRepository.save(invoice1);
        invoicesRepository.save(invoice2);
        long availableInvoices = invoicesRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();

        // Then
        assertEquals(currentNumberOfInvoices + 2, availableInvoices);

        // Clean Up
        invoicesRepository.deleteById(invoice1.getId());
        invoicesRepository.deleteById(invoice2.getId());
    }

    @Test
    public void shouldFindInvoiceById() {

        // Given
        Invoices invoice1 = new Invoices("1/2022", Date.from(Instant.now()), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");
        Invoices invoice2 = new Invoices("2/2022", Date.from(Instant.now()), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");

        // When
        invoicesRepository.save(invoice1);
        invoicesRepository.save(invoice2);
        Long invoice1Id = invoice1.getId();
        Optional<Invoices> foundInvoice = invoicesRepository.findById(invoice1Id);

        // Then
        assertNotNull(foundInvoice);
        assertEquals(invoice1Id, foundInvoice.get().getId());

        // Clean Up
        invoicesRepository.deleteById(invoice1Id);
        invoicesRepository.deleteById(invoice2.getId());
    }

    @Test
    public void shouldSaveInvoice() {

        // Given
        Invoices invoice = new Invoices("1/2022", Date.from(Instant.now()), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30), BigDecimal.valueOf(12.30),"gotówka");
//        Customers customer = new Customers("Jan Kowalski","5630016732", "Ulica", "22-100",
//                "Chełm");
        Customers customer = customersRepository.findById(2L).get();
//        Users user = new Users("Marian","5278451874", "Własna 2", "22-200",
//                "Włodawa");
        Users user = usersRepository.findById(1L).get();
//        Products product = new Products("name", 23,BigDecimal.valueOf(10.00),
//                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30));
        Products product = productsRepository.findById(5L).get();
        customer.getInvoicesList().add(invoice);
        user.getInvoicesList().add(invoice);
        product.getInvoicesList().add(invoice);
        invoice.setCustomer(customer);
        invoice.setUser(user);
        invoice.getProductsList().add(product);

        //When
        customersRepository.save(customer);
        usersRepository.save(user);
        productsRepository.save(product);
        invoicesRepository.save(invoice);
        Long customerId = customer.getId();
        Long userId = user.getId();
        Long productId = product.getId();
        Long invoiceId = invoice.getId();
        Optional<Invoices> savedInvoice = invoicesRepository.findById(invoiceId);
        Optional<Customers> savedCustomer = customersRepository.findById(customerId);
        Optional<Users> savedUser = usersRepository.findById(userId);
        Optional<Products> savedProduct = productsRepository.findById(productId);
        int customersSize = savedCustomer.get().getInvoicesList().size();
        int usersSize = savedUser.get().getInvoicesList().size();
        int productsSize = savedProduct.get().getInvoicesList().size();

        // Then
//        assertEquals(1, customersSize);
//        assertEquals(1, usersSize);
//        assertEquals(1, productsSize);
        assertEquals(customerId, savedCustomer.get().getId());
        assertEquals(userId, savedUser.get().getId());
        assertEquals(productId, savedProduct.get().getId());
        assertEquals(invoiceId, savedInvoice.get().getId());

        // Clean Up
//        customersRepository.deleteById(savedCustomer.get().getId());
//        usersRepository.deleteById(savedUser.get().getId());
//        productsRepository.deleteById(savedProduct.get().getId());
//        invoicesRepository.deleteById(savedInvoice.get().getId());
    }

    @Test
    public void shouldUpdateInvoice() {

        // Given
        Invoices invoice = new Invoices("1/2022", Date.from(Instant.now()), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");

        // When
        invoicesRepository.save(invoice);
        invoice.setNumber("02/2022");
        invoicesService.update(invoice);
        Long invoiceId = invoice.getId();
        Invoices actualInvoice = invoicesService.getOne(invoiceId);

        // Then
        assertTrue(invoicesRepository.existsById(invoiceId));
        assertEquals("02/2022", actualInvoice.getNumber());

        // Clean Up
        invoicesRepository.deleteById(invoiceId);
    }

    @Test
    public void shouldDeleteInvoiceById() {

        // Given
        long currentNumberOfInvoices = invoicesRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Invoices invoice1 = new Invoices("1/2022", Date.from(Instant.now()), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");
        Invoices invoice2 = new Invoices("2/2022", Date.from(Instant.now()), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");

        // When
        invoicesRepository.save(invoice1);
        invoicesRepository.save(invoice2);
        Long invoice1Id = invoice1.getId();
        invoicesRepository.deleteById(invoice1Id);
        Optional<Invoices> removedInvoice = invoicesRepository.findById(invoice1Id);
        long availableInvoices = invoicesRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();

        // Then
        assertEquals(Optional.empty(), removedInvoice);
        assertEquals(currentNumberOfInvoices + 1, availableInvoices);

        // Clean Up
        invoicesRepository.deleteById(invoice2.getId());
    }
}