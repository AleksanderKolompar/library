package com.kodilla.library.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TitleNotFoundException extends ResponseStatusException {

    public TitleNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Title was not found.");
    }
}
