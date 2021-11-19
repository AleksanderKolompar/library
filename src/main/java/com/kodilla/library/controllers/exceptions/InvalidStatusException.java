package com.kodilla.library.controllers.exceptions;

import com.kodilla.library.domain.Book;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidStatusException extends ResponseStatusException {
    public InvalidStatusException(Book.Status expected, Book.Status actual) {
        super(HttpStatus.CONFLICT, "Invalid Book Status:\nExpected: " + expected + "\nGot: " + actual);
    }
}
