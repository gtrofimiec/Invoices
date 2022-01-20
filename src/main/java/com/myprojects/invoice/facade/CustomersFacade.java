package com.myprojects.invoice.facade;

import com.myprojects.invoice.domain.Customers;
import com.myprojects.invoice.domain.dtos.CustomersDto;
import com.myprojects.invoice.exceptions.CustomerAlreadyExistsException;
import com.myprojects.invoice.exceptions.CustomerNotFoundException;
import com.myprojects.invoice.mappers.CustomersMapper;
import com.myprojects.invoice.services.CustomersService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class CustomersFacade {

    private final CustomersMapper customersMapper;
    private final CustomersService customersService;

    public List<CustomersDto> getCustomers() {
        return customersMapper.mapToCustomersDtoList(customersService.getAll());
    }

    public CustomersDto getCustomer(Long id) throws CustomerNotFoundException {
        return customersMapper.mapToCustomerDto(customersService.getOne(id));
    }

    public CustomersDto createCustomer(CustomersDto customerDto) throws CustomerNotFoundException,
            CustomerAlreadyExistsException {
        Customers newCustomer = customersMapper.mapToCustomer(customerDto);
        customersService.save(newCustomer);
        return customersMapper.mapToCustomerDto(newCustomer);
    }

    public CustomersDto updateCustomer(Long id, CustomersDto customerDto)
            throws CustomerNotFoundException {
        Customers updatedCustomer = customersMapper.mapToCustomer(customerDto);
        updatedCustomer.setId(id);
        customersService.update(updatedCustomer);
        return customersMapper.mapToCustomerDto(updatedCustomer);
    }

    public void deleteCustomer(Long id) throws CustomerNotFoundException {
        customersService.delete(id);
    }
}