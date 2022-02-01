package com.myprojects.invoice.repositories;

import com.myprojects.invoice.domain.Invoices;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface InvoicesRepository extends CrudRepository<Invoices, Long> {

    @Override
    @NotNull
    List<Invoices> findAll();

    @Override
    @NotNull
    Optional<Invoices> findById(Long invoiceId);

    @Override
    @NotNull
    Invoices save(Invoices invoice);

    @Override
    void deleteById(Long invoiceId);
}