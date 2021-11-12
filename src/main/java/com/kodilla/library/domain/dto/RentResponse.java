package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentResponse {

    public RentResponse() {
    }

    private Long id;
    private BookResponse bookResponse;
    private ReaderResponse readerResponse;
}
