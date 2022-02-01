package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.Customers;
import com.myprojects.invoice.domain.Invoices;
import com.myprojects.invoice.domain.Products;
import com.myprojects.invoice.domain.Users;
import com.myprojects.invoice.exceptions.*;
import com.myprojects.invoice.repositories.CustomersRepository;
import com.myprojects.invoice.repositories.InvoicesRepository;
import com.myprojects.invoice.repositories.ProductsRepository;
import com.myprojects.invoice.repositories.UsersRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class InvoicesService {

    private final InvoicesRepository invoicesRepository;
    private final CustomersService customersService;
    private final CustomersRepository customersRepository;
    private final ProductsService productsService;
    private final ProductsRepository productsRepository;
    private final UserService userService;
    private final UsersRepository usersRepository;

    public List<Invoices> getAll() {
        return invoicesRepository.findAll().stream()
                .filter(i -> !i.isDeleted())
                .collect(Collectors.toList());
    }

    public Invoices getOne(final Long id) throws InvoicesNotFoundException {
        return invoicesRepository.findById(id)
                .filter(i -> !i.isDeleted())
                .orElseThrow(InvoicesNotFoundException::new);
    }

    public Invoices save(final @NotNull Invoices invoice) throws InvoiceAlreadyExistsException {
        Invoices newInvoice = invoicesRepository.save(invoice);
        Customers addedCustomer = customersRepository.findById(invoice.getCustomer().getId())
                .orElseThrow(CustomerNotFoundException::new);
        addedCustomer.getInvoicesList().add(newInvoice);
        customersService.update(addedCustomer);

        Users addedUser = usersRepository.findById(invoice.getUser().getId())
                .orElseThrow(UserNotFoundException::new);
        addedUser.getInvoicesList().add(newInvoice);
        userService.update(addedUser);

        for(Products product : invoice.getProductsList()) {
            Products addedProduct = productsRepository.findById(product.getId())
                    .orElseThrow(ProductNotFoundException::new);
            addedProduct.getInvoicesList().add(newInvoice);
            productsRepository.save(addedProduct);
        }
        return newInvoice;
    }

    public Invoices addProductToInvoice(@NotNull Invoices invoice, final Long productId)
            throws InvoicesNotFoundException {
        Products updatedProduct = productsService.getOne(productId);
        Invoices updatedInvoice = invoicesRepository.findById(invoice.getId())
                .orElseThrow(InvoicesNotFoundException::new);
        updatedInvoice.getProductsList().add(updatedProduct);
        updatedProduct.getInvoicesList().add(updatedInvoice);
        invoicesRepository.save(updatedInvoice);
        productsRepository.save(updatedProduct);
        return updatedInvoice;
    }

    public Invoices addCustomerToInvoice(@NotNull Invoices invoice, final Long customerId)
            throws CustomerNotFoundException {
        Customers customer = customersService.getOne(customerId);
        invoice.setCustomer(customer);
        customer.getInvoicesList().add(invoice);
        invoicesRepository.save(invoice);
        customersRepository.save(customer);
        return invoice;
    }

    public Invoices update(final @NotNull Invoices invoice) throws InvoicesNotFoundException {
        Long id = invoice.getId();
        if (id == null || !invoicesRepository.existsById(id)) {
            throw new InvoicesNotFoundException();
        }
        return invoicesRepository.save(invoice);
    }

    public void deleteInvoice(final Long id) throws InvoicesNotFoundException {
        Invoices invoice = invoicesRepository.findById(id)
                .orElseThrow(InvoicesNotFoundException::new);
        invoice.setDeleted(true);
        invoicesRepository.save(invoice);
    }
    public Invoices deleteCustomerFromInvoice(@NotNull Invoices invoice, final Long customerId)
            throws CustomerNotFoundException {
        Customers customer = customersService.getOne(customerId);
        invoice.setCustomer(null);
        customer.getInvoicesList().remove(invoice);
        invoicesRepository.save(invoice);
        customersRepository.save(customer);
        return invoice;

    }
    public Invoices deleteProductFromInvoice(@NotNull Invoices invoice, final Long productId)
            throws ProductNotFoundException {
        Products product = productsService.getOne(productId);
        invoice.getProductsList().remove(product);
        product.getInvoicesList().remove(invoice);
        invoicesRepository.save(invoice);
        productsRepository.save(product);
        return invoice;
    }
}