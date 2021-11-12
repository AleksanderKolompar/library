package com.kodilla.library.mapper;

import com.kodilla.library.domain.Book;
import com.kodilla.library.domain.dto.BookResponse;
import com.kodilla.library.domain.dto.BookRequest;
import com.kodilla.library.domain.dto.TitleRequest;
import org.springframework.stereotype.Service;

@Service
public class BookMapper {

    private TitleMapper titleMapper;
    //private TitleService titleService;

    public BookResponse mapToBookResponse(Book book) {
        BookResponse bookResponse = new BookResponse();
        bookResponse.setId(book.getId());
        bookResponse.setTitleResponse(titleMapper.mapToTitleResponse(book.getTitle()));
        bookResponse.setStatus(book.getStatus().getValue());
        return bookResponse;
    }

    public Book mapToBook(BookRequest bookRequest) {
        Book book = new Book();
        book.setTitle(titleMapper.mapToTitle(new TitleRequest()));
        //titleService.getTitle(bookRequest.getTitleId())));
        book.setStatus(Book.Status.valueOf(bookRequest.getStatus()));
        return book;
    }

}
