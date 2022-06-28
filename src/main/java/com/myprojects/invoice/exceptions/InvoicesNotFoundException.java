package com.myprojects.invoice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvoicesNotFoundException extends ResponseStatusException {

    public InvoicesNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Invoice not found");
    }
}