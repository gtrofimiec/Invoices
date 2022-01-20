package com.myprojects.invoice.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class CeidgDataNotFoundException extends ResponseStatusException {

    public CeidgDataNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Data in CEIDG not found");
    }
}