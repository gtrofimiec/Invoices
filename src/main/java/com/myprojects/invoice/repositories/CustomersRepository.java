package com.myprojects.invoice.repositories;

import com.myprojects.invoice.domain.Customers;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomersRepository extends CrudRepository<Customers, Long> {

    @Override
    List<Customers> findAll();

    @Override
    Optional<Customers> findById(Long customerId);

    @Override
    Customers save(Customers customer);

    @Override
    void deleteById(Long customerId);
}