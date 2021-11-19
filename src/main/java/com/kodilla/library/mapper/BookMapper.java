package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    private final TitleMapper titleMapper;

    public BookMapper(TitleMapper titleMapper) {
        this.titleMapper = titleMapper;
    }

    public BookResponse mapToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitleResponse(titleMapper.mapToTitleResponse(book.getTitle()));
        bookResponse.setStatus(book.getStatus());
        return bookResponse;
    }

    public List<BookResponse> mapToBookResponseList(List<Book> books) {
        return books.stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

}
