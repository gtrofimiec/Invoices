package com.myprojects.invoice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerAlreadyExistsException extends ResponseStatusException {

    public CustomerAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Customer already exist");
    }
}