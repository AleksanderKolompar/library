package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Books;
import com.kodilla.library.domain.Readers;
import lombok.Data;

@Data
public class RentDto {

    private Long id;
    private Books bookId;
    private Readers readerId;
}
