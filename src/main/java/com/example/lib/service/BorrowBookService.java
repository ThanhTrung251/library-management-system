package com.example.lib.service;


import com.example.lib.model.dto.BorrowBookDTO;
import com.example.lib.model.dto.BorrowedBook;
import com.example.lib.model.entity.BorrowBook;
import com.example.lib.model.request.BorrowBookRequest;

import java.util.List;

public interface BorrowBookService {
    List<BorrowedBook> getAllBorrowBook(String keyword);

    BorrowBook createReverseBook(BorrowBookRequest borrowBookRequest);

    BorrowBook updateBorrowBook(long id, BorrowBook borrowBook);

    BorrowBookDTO returnBorrowBook(long id);

    BorrowBookDTO getBorrowBookById(long id);

    BorrowBook creatBorrowBook(BorrowBookRequest borrowBookRequest);
}
