package com.kodilla.library.controllers.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class TitleExistsException extends ResponseStatusException {

    public TitleExistsException() {
        super(HttpStatus.CONFLICT, "Title already exist.");
    }
}
