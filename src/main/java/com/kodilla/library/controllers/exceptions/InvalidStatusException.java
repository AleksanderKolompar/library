package com.kodilla.library.controllers.exceptions;

public class InvalidStatusException extends Exception {
    public InvalidStatusException() {
        super("Invalid Book Status");
    }
}
