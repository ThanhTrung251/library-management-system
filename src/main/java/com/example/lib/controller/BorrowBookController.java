package com.example.lib.controller;

import com.example.lib.model.dto.BorrowedBook;
import com.example.lib.model.request.BorrowBookRequest;
import com.example.lib.service.BookService;
import com.example.lib.service.BorrowBookService;
import com.example.lib.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
public class BorrowBookController {
    private final MemberService memberService;
    private final BookService bookService;
    private final BorrowBookService borrowBookService;

    public BorrowBookController(MemberService memberService, BookService bookService, BorrowBookService borrowBookService) {
        this.memberService = memberService;
        this.bookService = bookService;
        this.borrowBookService = borrowBookService;
    }

    @GetMapping("/books/borrowed")
    public String borrowBook(Model model, @RequestParam(defaultValue = "") String keyword) {
        List<BorrowedBook> borrowBooks = borrowBookService.getAllBorrowBook(keyword);
        model.addAttribute("borrowBooks", borrowBooks);
        return "book/borrow-book-list";
    }

    @GetMapping("book/borrow")
    public String borrowBook(Model model) {
        model.addAttribute("borrowBook", new BorrowBookRequest());
        model.addAttribute("members", memberService.getAllMember());
        model.addAttribute("books", bookService.getAllBookAvailable());
        model.addAttribute("createDate", LocalDate.now());
        return "book/borrow";
    }

    @PostMapping("/book/borrow")
    public ModelAndView doBorrowBook(@ModelAttribute("borrowBook") @Valid BorrowBookRequest borrowBookRequest, RedirectAttributes flashSession) {
        borrowBookService.creatBorrowBook(borrowBookRequest);
        flashSession.addFlashAttribute("success", "Mượn sách thành công");
        return new ModelAndView("redirect:/book/borrow");

    }
}
