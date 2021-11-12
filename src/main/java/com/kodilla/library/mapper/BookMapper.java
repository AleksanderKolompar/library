package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookDto;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    TitleMapper titleMapper;

    public BookDto mapToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitleDto(titleMapper.mapToTileDto(book.getTitleId()));
        bookDto.setStatus(book.getStatus());
        return bookDto;
    }

    public Book mapToBook(BookDto bookDto) {
        Book book = new Book();
        book.setTitleId(titleMapper.mapToTitles(bookDto.getTitleDto()));
        book.setStatus(bookDto.getStatus());
        return book;
    }

}
