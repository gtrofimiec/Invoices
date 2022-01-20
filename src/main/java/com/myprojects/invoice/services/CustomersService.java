package com.myprojects.invoice.services;

import com.myprojects.invoice.domain.Customers;
import com.myprojects.invoice.exceptions.CustomerAlreadyExistsException;
import com.myprojects.invoice.exceptions.CustomerNotFoundException;
import com.myprojects.invoice.repositories.CustomersRepository;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
@Transactional
public class CustomersService {

    private final CustomersRepository customersRepository;

    public List<Customers> getAll() {
        return customersRepository.findAll().stream()
                .filter(c -> !c.isDeleted())
                .collect(Collectors.toList());
    }

    public Customers getOne(final Long id) throws CustomerNotFoundException {
        return customersRepository.findById(id)
                .filter(c -> !c.isDeleted())
                .orElseThrow(CustomerNotFoundException::new);
    }

    public Customers save(final @NotNull Customers customer) throws CustomerAlreadyExistsException {
        Long id = customer.getId();
        if (id != null && customersRepository.existsById(id)) {
            throw new CustomerAlreadyExistsException();
        }
        return customersRepository.save(customer);
    }

    public Customers update(final @NotNull Customers customer) throws CustomerNotFoundException {
        Long id = customer.getId();
        if (id == null || !customersRepository.existsById(id)) {
            throw new CustomerNotFoundException();
        }
        return customersRepository.save(customer);
    }

    public void delete(final Long id) throws CustomerNotFoundException {
        Customers customer = customersRepository.findById(id)
                .orElseThrow(CustomerNotFoundException::new);
        customer.setDeleted(true);
        customersRepository.save(customer);
    }
}