package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Book;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDto {

    public BookDto() {
    }

    private Long id;
    private TitleDto titleDto;
    private Book.Status status;

}
