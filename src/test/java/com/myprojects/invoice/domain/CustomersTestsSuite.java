package com.myprojects.invoice.domain;

import com.myprojects.invoice.facade.CustomersFacade;
import com.myprojects.invoice.repositories.CustomersRepository;
import com.myprojects.invoice.services.CustomersService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CustomersTestsSuite {

    @Autowired
    private CustomersRepository customersRepository;
    @Autowired
    private CustomersFacade customersFacade;
    @Autowired
    private CustomersService customersService;

    @Test
    public void shouldFindAllCustomers() {

        // Given
        long currentNumberOfCustomers = customersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Customers customer1 = new Customers();
        Customers customer2 = new Customers();

        // When
        customersRepository.save(customer1);
        customersRepository.save(customer2);
        long availableCustomers = customersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();

        // Then
        assertEquals(currentNumberOfCustomers + 2, availableCustomers);

        // Clean Up
        customersRepository.deleteById(customer1.getId());
        customersRepository.deleteById(customer2.getId());
    }

    @Test
    public void shouldFindCustomerById() {

        // Given
        Customers customer1 = new Customers();
        Customers customer2 = new Customers();

        // When
        customersRepository.save(customer1);
        customersRepository.save(customer2);
        Long customer1Id = customer1.getId();
        Optional<Customers> foundCustomer = customersRepository.findById(customer1Id);

        // Then
        assertNotNull(foundCustomer);
        assertEquals(customer1Id, foundCustomer.get().getId());

        // Clean Up
        customersRepository.deleteById(customer1Id);
        customersRepository.deleteById(customer2.getId());
    }

    @Test
    public void shouldSaveCustomer() {

        // Given
        Customers customer1 = new Customers();
        Customers customer2 = new Customers();
        customer1.setNip("5630016732");
        customer2.setNip("7125398412");

        // When
        customersRepository.save(customer1);
        customersRepository.save(customer2);
        Long customer1Id = customer1.getId();
        Long customer2Id = customer2.getId();
        Optional<Customers> savedCustomer1 = customersRepository.findById(customer1Id);
        Optional<Customers> savedCustomer2 = customersRepository.findById(customer2Id);
        String nip1 = savedCustomer1.get().getNip();
        String nip2 = savedCustomer2.get().getNip();

        // Then
        assertEquals("5630016732", nip1);
        assertEquals("7125398412", nip2);

        // Clean Up
        customersRepository.deleteById(customer1Id);
        customersRepository.deleteById(customer2Id);
    }

    @Test
    public void shouldUpdateCustomer() {

        // Given
        Customers customer = new Customers();

        // When
        customersService.save(customer);
        customer.setStreet("Street");
        customersService.update(customer);
        Long customerId = customer.getId();
        Customers actualCustomer = customersService.getOne(customerId);

        // Then
        assertTrue(customersRepository.existsById(customerId));
        assertEquals("Street", actualCustomer.getStreet());

        // Clean Up
        customersRepository.deleteById(customerId);
    }

    @Test
    public void shouldDeleteCustomerById() {

        // Given
        long currentNumberOfCustomers = customersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();
        Customers customer1 = new Customers();
        Customers customer2 = new Customers();

        // When
        customersRepository.save(customer1);
        customersRepository.save(customer2);
        Long customer1Id = customer1.getId();
        customersRepository.deleteById(customer1Id);
        Optional<Customers> removedCustomer = customersRepository.findById(customer1Id);
        long availableCustomers = customersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .count();

        // Then
        assertEquals(Optional.empty(), removedCustomer);
        assertEquals(currentNumberOfCustomers + 1, availableCustomers);

        // Clean Up
        customersRepository.deleteById(customer2.getId());
    }
}