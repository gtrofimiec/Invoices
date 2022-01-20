package com.myprojects.invoice.domain;

import com.myprojects.invoice.repositories.InvoicesRepository;
import com.myprojects.invoice.services.InvoicesService;
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
public class InvoicesTestSuite {

    @Autowired
    private InvoicesRepository invoicesRepository;

    @Autowired
    private InvoicesService invoicesService;

    @Test
    public void shouldFindAllInvoices() {

        // Given
        Invoices invoice1 = new Invoices();
        Invoices invoice2 = new Invoices();

        // When
        invoicesRepository.save(invoice1);
        invoicesRepository.save(invoice2);

        // Then
        assertEquals(2, invoicesRepository.findAll().size());

        // Clean Up
        invoicesRepository.deleteById(invoice1.getId());
        invoicesRepository.deleteById(invoice2.getId());
    }

    @Test
    public void shouldFindInvoiceById() {

        // Given
        Invoices invoice1 = new Invoices();
        Invoices invoice2 = new Invoices();

        // When
        invoicesRepository.save(invoice1);
        invoicesRepository.save(invoice2);
        Long id = invoice1.getId();
        Optional<Invoices> foundInvoice = invoicesRepository.findById(id);

        // Then
        assertNotNull(foundInvoice);
        assertEquals(id, foundInvoice.get().getId());

        // Clean Up
        invoicesRepository.deleteById(invoice1.getId());
        invoicesRepository.deleteById(invoice2.getId());
    }

    @Test
    public void shouldSaveInvoice() {

        // Given
        Invoices invoice1 = new Invoices();
        Invoices invoice2 = new Invoices();
        invoice1.setNumber("01/2022");
        invoice2.setNumber("02/2022");

        // When
        invoicesRepository.save(invoice1);
        invoicesRepository.save(invoice2);
        Long invoice1Id = invoice1.getId();
        Long invoice2Id = invoice2.getId();
        Optional<Invoices> savedInvoice1 = invoicesRepository.findById(invoice1Id);
        Optional<Invoices> savedInvoice2 = invoicesRepository.findById(invoice2Id);
        String number1 = savedInvoice1.get().getNumber();
        String number2 = savedInvoice2.get().getNumber();

        // Then
        assertEquals("01/2022", number1);
        assertEquals("02/2022", number2);

        // Clean Up
        invoicesRepository.deleteById(invoice1.getId());
        invoicesRepository.deleteById(invoice2.getId());
    }

    @Test
    public void shouldUpdateInvoice() {

        // Given
        Invoices invoice = new Invoices();
        Invoices updatedInvoice = new Invoices();
        invoice.setNumber("01/2022");
        updatedInvoice.setNumber("02/2022");

        // When
        invoicesService.save(invoice);
        Long id = invoice.getId();
        updatedInvoice.setId(id);
        invoicesService.update(updatedInvoice);
        Invoices actualInvoice = invoicesService.getOne(id);

        // Then
        assertTrue(invoicesRepository.existsById(id));
        assertEquals(updatedInvoice.getId(), actualInvoice.getId());
        assertEquals(updatedInvoice.getNumber(), actualInvoice.getNumber());
        assertNotEquals(invoice.getNumber(), actualInvoice.getNumber());

        // Clean Up
        invoicesRepository.deleteById(invoice.getId());
    }

    @Test
    public void shouldDeleteInvoiceById() {

        // Given
        Invoices invoice1 = new Invoices();
        Invoices invoice2 = new Invoices();

        // When
        invoicesRepository.save(invoice1);
        invoicesRepository.save(invoice2);
        Long id = invoice1.getId();
        invoicesRepository.deleteById(id);
        Optional<Invoices> removedInvoice = invoicesRepository.findById(id);
        int storedInvoices = invoicesRepository.findAll().size();

        // Then
        assertEquals(Optional.empty(), removedInvoice);
        assertEquals(1, storedInvoices);

        // Clean Up
        invoicesRepository.deleteById(invoice2.getId());
    }
}