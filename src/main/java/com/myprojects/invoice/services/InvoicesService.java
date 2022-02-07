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

    public Invoices update(final @NotNull Invoices invoice) throws InvoicesNotFoundException {
        Long id = invoice.getId();
        if (id == null || !invoicesRepository.existsById(id)) {
            throw new InvoicesNotFoundException();
        }
        return invoicesRepository.save(invoice);
    }

    public Invoices addProductToInvoice(@NotNull Invoices invoice, final Long productId)
            throws InvoicesNotFoundException {
        Products addedProduct = productsRepository.findById(productId)
                .orElseThrow(ProductNotFoundException::new);
        Invoices updatedInvoice = invoicesRepository.findById(invoice.getId())
                .orElseThrow(InvoicesNotFoundException::new);
        updatedInvoice.getProductsList().add(addedProduct);
        addedProduct.getInvoicesList().add(updatedInvoice);
        invoicesRepository.save(updatedInvoice);
        productsRepository.save(addedProduct);
        return updatedInvoice;
    }

    public Invoices addCustomerToInvoice(@NotNull Invoices invoice, final Long customerId)
            throws CustomerNotFoundException {
        Customers addedCustomer = customersRepository.findById(customerId)
                .orElseThrow(CustomerNotFoundException::new);
        Invoices updatedInvoice = invoicesRepository.findById(invoice.getId())
                .orElseThrow(InvoicesNotFoundException::new);
        updatedInvoice.setCustomer(addedCustomer);
        addedCustomer.getInvoicesList().add(updatedInvoice);
        invoicesRepository.save(updatedInvoice);
        customersRepository.save(addedCustomer);
        return invoice;
    }

    public Invoices addUserToInvoice(@NotNull Invoices invoice, final Long userId)
            throws UserNotFoundException {
        Users addedUser = usersRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        Invoices updatedInvoice = invoicesRepository.findById(invoice.getId())
                .orElseThrow(InvoicesNotFoundException::new);
        updatedInvoice.setUser(addedUser);
        addedUser.getInvoicesList().add(updatedInvoice);
        invoicesRepository.save(updatedInvoice);
        usersRepository.save(addedUser);
        return invoice;
    }

    public void deleteInvoice(final Long id) throws InvoicesNotFoundException {
        Invoices invoice = invoicesRepository.findById(id)
                .orElseThrow(InvoicesNotFoundException::new);
        invoice.setDeleted(true);
        invoicesRepository.save(invoice);
    }
    public Invoices deleteCustomerFromInvoice(@NotNull Invoices invoice, final Long customerId)
            throws CustomerNotFoundException {
        Invoices updatedInvoice = invoicesRepository.findById(invoice.getId())
                .orElseThrow(InvoicesNotFoundException::new);
        Customers customer = customersService.getOne(customerId);
        updatedInvoice.setCustomer(null);
        customer.getInvoicesList().remove(updatedInvoice);
        invoicesRepository.save(updatedInvoice);
        customersRepository.save(customer);
        return updatedInvoice;

    }
    public Invoices deleteProductFromInvoice(@NotNull Invoices invoice, final Long productId)
            throws ProductNotFoundException {
        Invoices updatedInvoice = invoicesRepository.findById(invoice.getId())
                .orElseThrow(InvoicesNotFoundException::new);
        Products product = productsService.getOne(productId);
        updatedInvoice.getProductsList().remove(product);
        product.getInvoicesList().remove(updatedInvoice);
        invoicesRepository.save(updatedInvoice);
        productsRepository.save(product);
        return updatedInvoice;
    }

    public Invoices deleteUserFromInvoice(@NotNull Invoices invoice, final Long userId)
            throws UserNotFoundException {
        Invoices updatedInvoice = invoicesRepository.findById(invoice.getId())
                .orElseThrow(InvoicesNotFoundException::new);
        Users user = userService.getOne(userId);
        updatedInvoice.setUser(null);
        user.getInvoicesList().remove(updatedInvoice);
        invoicesRepository.save(updatedInvoice);
        usersRepository.save(user);
        return updatedInvoice;
    }
}