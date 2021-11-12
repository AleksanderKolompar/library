package com.kodilla.library.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ReaderExistsException extends ResponseStatusException {

    public ReaderExistsException() {
        super(HttpStatus.CONFLICT, "Reader already exist.");
    }
}
