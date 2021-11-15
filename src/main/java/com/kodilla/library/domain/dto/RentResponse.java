package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class RentResponse {

    public RentResponse() {
    }

    private Long id;
    private BookResponse bookResponse;
    private ReaderResponse readerResponse;
    private LocalDate rentDate;
    private LocalDate returnDate;
}
