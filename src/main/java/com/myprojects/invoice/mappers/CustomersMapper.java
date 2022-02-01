package com.myprojects.invoice.mappers;

import com.myprojects.invoice.domain.Customers;
import com.myprojects.invoice.domain.dtos.CustomersDto;
import com.myprojects.invoice.exceptions.CustomerNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomersMapper {

    public Customers mapToCustomer(final @NotNull CustomersDto customerDto)
            throws CustomerNotFoundException {
        Customers customer = new Customers();
            customer.setId(customerDto.getId());
            customer.setFullName(customerDto.getFullName());
            customer.setNip(customerDto.getNip());
            customer.setStreet(customerDto.getStreet());
            customer.setPostCode(customerDto.getPostCode());
            customer.setTown(customerDto.getTown());
        return customer;
    }

    public CustomersDto mapToCustomerDto(final @NotNull Customers customer)
            throws CustomerNotFoundException {
        CustomersDto customerDto = new CustomersDto();
            customerDto.setId(customer.getId());
            customerDto.setFullName(customer.getFullName());
            customerDto.setNip(customer.getNip());
            customerDto.setStreet(customer.getStreet());
            customerDto.setPostCode(customer.getPostCode());
            customerDto.setTown(customer.getTown());
        return customerDto;
    }

    public List<CustomersDto> mapToCustomersDtoList(final @NotNull List<Customers> customersList) {
        return customersList.stream()
                .map(this::mapToCustomerDto)
                .collect(Collectors.toList());
    }
}