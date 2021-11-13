package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookRequest;
import com.kodilla.library.domain.dto.BookResponse;
import com.kodilla.library.service.TitleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    private TitleMapper titleMapper;
    private TitleService titleService;

    public BookResponse mapToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitleResponse(titleMapper.mapToTitleResponse(book.getTitle()));
        bookResponse.setStatus(book.getStatus().getValue());
        return bookResponse;
    }

    public Book mapToBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(titleService.get(bookRequest.getTitleId()));
        return book;
    }

    public List<BookResponse> mapToBookResponseList(List<Book> books) {
        return books.stream()
                .map(this::mapToBookResponse)
                .collect(Collectors.toList());
    }

}
