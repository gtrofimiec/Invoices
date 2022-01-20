package com.myprojects.invoice.controllers;

import com.myprojects.invoice.domain.dtos.CustomersDto;
import com.myprojects.invoice.exceptions.CustomerAlreadyExistsException;
import com.myprojects.invoice.exceptions.CustomerNotFoundException;
import com.myprojects.invoice.facade.CustomersFacade;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@AllArgsConstructor
@RestController
@RequestMapping("v1/invoices")
public class CustomersController {

    private final CustomersFacade customersFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/customers")
    public List<CustomersDto> getCustomers() {
        return customersFacade.getCustomers();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/customers/{id}")
    public CustomersDto getCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        return customersFacade.getCustomer(customerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/customers",
            consumes = APPLICATION_JSON_VALUE)
    public CustomersDto createCustomer(@RequestBody CustomersDto customerDto) throws CustomerNotFoundException,
            CustomerAlreadyExistsException {
        return customersFacade.createCustomer(customerDto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/customers/{id}")
    public CustomersDto updateCustomer(@PathVariable("id") Long customerId, @RequestBody CustomersDto customerDto)
            throws CustomerNotFoundException {
        return customersFacade.updateCustomer(customerId, customerDto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/customers/{id}")
    public void deleteCustomer(@PathVariable("id") Long customerId) throws CustomerNotFoundException {
        customersFacade.deleteCustomer(customerId);
    }
}