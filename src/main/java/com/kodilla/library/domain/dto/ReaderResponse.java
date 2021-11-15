package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ReaderResponse {

    public ReaderResponse() {
    }

    private Long id;
    private String firstname;
    private String lastname;
    private LocalDate registrationDate;
}
