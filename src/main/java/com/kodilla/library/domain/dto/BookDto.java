package com.kodilla.library.domain.dto;

import com.kodilla.library.domain.Books;
import com.kodilla.library.domain.Titles;
import lombok.Data;

@Data
public class BookDto {

    private Long id;
    private Titles titles;
    private Books.Status status;

}
