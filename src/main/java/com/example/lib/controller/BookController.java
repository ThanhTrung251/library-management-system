package com.example.lib.controller;

import com.example.lib.model.dto.BookAfterFilter;
import com.example.lib.model.dto.BookDTO;
import com.example.lib.model.dto.BorrowBookDTO;
import com.example.lib.model.request.BookRequest;
import com.example.lib.model.request.BorrowBookRequest;
import com.example.lib.model.request.SearchBookForm;
import com.example.lib.repository.BookRepository;
import com.example.lib.repository.MemberRepository;
import com.example.lib.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
@RequiredArgsConstructor
@Controller
public class BookController {
    private final BookService bookService;

    private final PublisherService publisherService;

    private final AuthorService authorService;

    private final MemberService memberService;

    private final BorrowBookService borrowBookService;

    private final MemberRepository memberRepository;

    private final BookRepository bookRepository;



    @GetMapping("/books/add")
    public String add(Model model) {
        model.addAttribute("book", new BookRequest());
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("publishers", publisherService.getAllPublisher());
        model.addAttribute("creatAt", LocalDate.now());
        return "book/book";
    }

    @GetMapping("/books")
    public String listBook(Model model) {
        List<BookAfterFilter> books = bookService.listAllBook();
        model.addAttribute("books", books);
        model.addAttribute("searchBookForm", new SearchBookForm());
        return "book/books";
    }

    @PostMapping("/books/search")
    public String searchBook(Model model, @ModelAttribute("searchBookForm") SearchBookForm request) {
        List<BookAfterFilter> books = bookService.searchBook(request);
        model.addAttribute("books", books);
        return "book/books";
    }

    @PostMapping("/api/v1/book")
    public ResponseEntity<?> doAdd(@RequestBody @Valid BookRequest bookRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.createBook(bookRequest));

    }

    @GetMapping("/books/reserve")
    public String reserve(Model model) {
        model.addAttribute("borrowBook", new BorrowBookRequest());
        model.addAttribute("members", memberRepository.findAll());
        model.addAttribute("reservebooks", bookRepository.findAll());
        model.addAttribute("creatDate", LocalDate.now());
        return "book/reserve-book";
    }

    @PostMapping("/books/reserve")
    public ModelAndView doReserve(@ModelAttribute("borrowBook") @Valid BorrowBookRequest borrowBookRequest, BindingResult bindingResult)
            throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        try {
            borrowBookService.createReverseBook(borrowBookRequest);
            return new ModelAndView("redirect:/books/borrowed");
        } catch (Exception ex) {
            //todo:
            ModelAndView a = new ModelAndView("redirect:/books/reserve");
            a.addObject(borrowBookRequest);
            a.addObject("errorMss", ex.getMessage());
            return a;
        }
    }

    @GetMapping("/books/update")
    public String Update(Model model, @RequestParam Long id) {
        BookDTO book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("publishers", publisherService.getAllPublisher());
        return "book/update-book";
    }

    @PostMapping("/api/v1/books/update")
    public ResponseEntity<?> doUpdate(@RequestParam Long id, @RequestBody @Valid BookRequest bookRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, bookRequest));
    }

    @GetMapping("/books/return")
    public String returnBook(Model model, @RequestParam Long id) {
        BorrowBookDTO borrowBook = borrowBookService.getBorrowBookById(id);
        model.addAttribute("borrowBooks", borrowBook);
        return "book/return-book";
    }

}