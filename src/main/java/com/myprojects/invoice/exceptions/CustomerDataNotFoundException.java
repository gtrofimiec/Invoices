package com.myprojects.invoice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CustomerDataNotFoundException extends ResponseStatusException {

    public CustomerDataNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Data for this customer not found");
    }
}