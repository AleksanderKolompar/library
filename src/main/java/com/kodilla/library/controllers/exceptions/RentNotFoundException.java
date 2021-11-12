package com.kodilla.library.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class RentNotFoundException extends ResponseStatusException {

    public RentNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Rent record was not found.");
    }
}
