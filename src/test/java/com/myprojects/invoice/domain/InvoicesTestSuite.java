package com.myprojects.invoice.domain;

import com.myprojects.invoice.repositories.CustomersRepository;
import com.myprojects.invoice.repositories.InvoicesRepository;
import com.myprojects.invoice.repositories.ProductsRepository;
import com.myprojects.invoice.repositories.UsersRepository;
import com.myprojects.invoice.services.CustomersService;
import com.myprojects.invoice.services.InvoicesService;
import com.myprojects.invoice.services.ProductsService;
import com.myprojects.invoice.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InvoicesTestSuite {

    @Autowired
    private InvoicesRepository invoicesRepository;
    @Autowired
    private InvoicesService invoicesService;
    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private CustomersService customersService;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductsService productsService;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserService userService;

    @Test
    public void shouldFindAllInvoices() {

        // Given
        long currentNumberOfInvoices = invoicesRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Invoices invoice1 = new Invoices("1/2022", LocalDate.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka", LocalDate.now());
        Invoices invoice2 = new Invoices("2/2022", LocalDate.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka", LocalDate.now());

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
        Invoices invoice1 = new Invoices("1/2022", LocalDate.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30), "gotówka", LocalDate.now());
        Invoices invoice2 = new Invoices("2/2022", LocalDate.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30), "gotówka",LocalDate.now());

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
    public void shouldSaveAndDeleteInvoice() {

        // Given
        Invoices invoice = new Invoices("1/2022", LocalDate.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30), "gotówka", LocalDate.now());
        Customers customer = new Customers("Jan Kowalski","5630016732", "Ulica", "22-100",
                "Chełm");
        Users user = new Users("Marian","5278451874", "Własna 2", "22-200",
                "Włodawa", "bank", "path", "login", "pass");
        Products product = new Products("name", "51.20-51.20", "item", 23, BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30));

        // When
        Customers addedCustomer = customersService.save(customer);
        Products addedProduct = productsService.save(product);
        Users addedUser = userService.save(user);
        Invoices newInvoice = invoicesRepository.save(invoice);
        Long addedCustomerDtoId = addedCustomer.getId();
        Long addedProductDtoId = addedProduct.getId();
        Long addedUserDtoId = addedUser.getId();
        Long addedInvoiceId = newInvoice.getId();
        newInvoice.setCustomer(addedCustomer);
        newInvoice.setUser(addedUser);
        newInvoice.getProductsList().add(addedProduct);
        invoicesService.update(newInvoice);

        // Then
        assertEquals(addedUserDtoId, invoice.getUser().getId());
        assertEquals(addedProductDtoId, invoice.getProductsList().get(0).getId());
        assertEquals(addedCustomerDtoId, invoice.getCustomer().getId());

        // Clean Up
        invoicesService.deleteCustomerFromInvoice(newInvoice, addedCustomerDtoId);
        invoicesService.deleteProductFromInvoice(newInvoice, addedProductDtoId);
        invoicesService.deleteUserFromInvoice(newInvoice, addedUserDtoId);
        customersRepository.deleteById(addedCustomerDtoId);
        productsRepository.deleteById(addedProductDtoId);
        usersRepository.deleteById(addedUserDtoId);
        invoicesRepository.deleteById(addedInvoiceId);
    }

    @Test
    public void shouldDeleteInvoiceById() {

        // Given
        long currentNumberOfInvoices = invoicesRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Invoices invoice1 = new Invoices("1/2022", LocalDate.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30), "gotówka", LocalDate.now());
        Invoices invoice2 = new Invoices("2/2022", LocalDate.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30), "gotówka", LocalDate.now());

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