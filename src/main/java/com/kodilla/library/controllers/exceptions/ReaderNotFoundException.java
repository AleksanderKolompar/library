package com.kodilla.library.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReaderNotFoundException extends ResponseStatusException {

    public ReaderNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Reader was not found.");
    }
}
