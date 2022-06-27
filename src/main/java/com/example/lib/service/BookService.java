package com.example.lib.service;

import com.example.lib.model.dto.BookAfterFilter;
import com.example.lib.model.dto.BookDTO;
import com.example.lib.model.request.BookRequest;
import com.example.lib.model.request.SearchBookForm;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBook();

    BookDTO createBook(BookRequest bookRequest);

    List<BookAfterFilter> listAllBook();

    List<BookAfterFilter> searchBook(SearchBookForm request);

    BookDTO updateBook(long id, BookRequest bookRequest);

    Boolean deleteBook(long id);

    BookDTO getBookById(long id);

    List<BookDTO> getAllBookAvailable();
}
