package com.myprojects.invoice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerNotFoundException extends ResponseStatusException {

    public CustomerNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Customer not found");
    }
}