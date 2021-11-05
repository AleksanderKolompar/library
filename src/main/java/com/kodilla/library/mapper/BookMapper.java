package com.kodilla.library.mapper;

import com.kodilla.library.domain.Books;
import com.kodilla.library.domain.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    public BookDto mapToBookDto(Books books){
        BookDto bookDto = new BookDto();
        bookDto.setId(books.getId());
        bookDto.setTitles(books.getTitleId());
        bookDto.setStatus(books.getStatus());
        return bookDto;
    }

    public Books mapToBooks(BookDto bookDto){
        Books books = new Books();
        books.setTitleId(bookDto.getTitles());
        books.setStatus(bookDto.getStatus());
        return books;
    }

}
