package com.myprojects.invoice.domain;

import com.myprojects.invoice.domain.dtos.CustomersDto;
import com.myprojects.invoice.domain.dtos.ProductsDto;
import com.myprojects.invoice.domain.dtos.UsersDto;
import com.myprojects.invoice.facade.CustomersFacade;
import com.myprojects.invoice.facade.ProductsFacade;
import com.myprojects.invoice.facade.UserFacade;
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
import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

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
    private CustomersFacade customersFacade;
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private ProductsFacade productsFacade;
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserFacade userFacade;


    @Test
    public void shouldFindAllInvoices() {

        // Given
        long currentNumberOfInvoices = invoicesRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Invoices invoice1 = new Invoices("1/2022", LocalDateTime.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");
        Invoices invoice2 = new Invoices("2/2022", LocalDateTime.now(), BigDecimal.valueOf(10.00),
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
        Invoices invoice1 = new Invoices("1/2022", LocalDateTime.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");
        Invoices invoice2 = new Invoices("2/2022", LocalDateTime.now(), BigDecimal.valueOf(10.00),
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
    public void shouldSaveAndDeleteInvoice() {

        // Given
        Invoices invoice = new Invoices("1/2022", LocalDateTime.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30), BigDecimal.valueOf(12.30),"gotówka");
        CustomersDto customerDto = new CustomersDto("Jan Kowalski","5630016732", "Ulica", "22-100",
                "Chełm");
        UsersDto userDto = new UsersDto("Marian","5278451874", "Własna 2", "22-200",
                "Włodawa", false);
        ProductsDto productDto = new ProductsDto("name", 23,BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30));

        //When
        CustomersDto addedCustomer = customersFacade.saveCustomer(customerDto);
        ProductsDto addedProductDto = productsFacade.saveProduct(productDto);
        UsersDto addedUserDto = userFacade.saveUser(userDto);
        Invoices newInvoice = invoicesRepository.save(invoice);
        Long addedCustomerDtoId = addedCustomer.getId();
        Long addedProductDtoId = addedProductDto.getId();
        Long addedUserDtoId = addedUserDto.getId();
        Long addedInvoiceId = newInvoice.getId();
        invoicesService.addCustomerToInvoice(newInvoice, addedCustomerDtoId);
        invoicesService.addProductToInvoice(newInvoice, addedProductDtoId);
        invoicesService.addUserToInvoice(newInvoice, addedUserDtoId);
        Optional<Invoices> savedInvoice = invoicesRepository.findById(addedInvoiceId);
        Optional<Customers> savedCustomer = customersRepository.findById(addedCustomerDtoId);
        Optional<Users> savedUser = usersRepository.findById(addedUserDtoId);
        Optional<Products> savedProduct = productsRepository.findById(addedProductDtoId);

        // Then
        assertEquals(addedCustomerDtoId, savedCustomer.get().getId());
        assertEquals(addedUserDtoId, savedUser.get().getId());
        assertEquals(addedProductDtoId, savedProduct.get().getId());
        assertEquals(addedInvoiceId, savedInvoice.get().getId());

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
    public void shouldUpdateInvoice() {

        // Given
        Invoices invoice = new Invoices("1/2022", LocalDateTime.now(), BigDecimal.valueOf(10.00),
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
        Invoices invoice1 = new Invoices("1/2022", LocalDateTime.now(), BigDecimal.valueOf(10.00),
                BigDecimal.valueOf(2.30),BigDecimal.valueOf(12.30),"gotówka");
        Invoices invoice2 = new Invoices("2/2022", LocalDateTime.now(), BigDecimal.valueOf(10.00),
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