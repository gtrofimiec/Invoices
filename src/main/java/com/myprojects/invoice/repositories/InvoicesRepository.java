package com.myprojects.invoice.repositories;

import com.myprojects.invoice.domain.Invoices;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvoicesRepository extends CrudRepository<Invoices, Long> {

    @Override
    List<Invoices> findAll();

    @Override
    Optional<Invoices> findById(Long invoiceId);

    @Override
    Invoices save(Invoices invoice);

    @Override
    void deleteById(Long invoiceId);
}