package com.kodilla.library.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RentDto {

    public RentDto() {
    }

    private Long id;
    private BookDto bookDto;
    private ReaderDto readerDto;
}
