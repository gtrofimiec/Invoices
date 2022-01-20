package com.myprojects.invoice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvoiceAlreadyExistsException extends ResponseStatusException {

    public InvoiceAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "Invoice already exist");
    }
}