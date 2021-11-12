package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookResponse {

    public BookResponse() {
    }

    private Long id;
    private TitleResponse titleResponse;
    private String status;

}
