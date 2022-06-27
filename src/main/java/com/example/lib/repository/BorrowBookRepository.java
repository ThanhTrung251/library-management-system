package com.example.lib.repository;

import com.example.lib.model.dto.BorrowedBook;
import com.example.lib.model.entity.BorrowBook;
import com.example.lib.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    @Query(value = "SELECT bb.id as borrowBookID, m.name as name, b.id as bookId, b.subject as bookSubject, b.title as bookTitle, a.name as authorName," +
            " p.name as publisherName, b.copyright as copyright, b.edition as edition,"
            + " b.pages as pages, b.library as library FROM book b "
            + "INNER JOIN author_book ab ON ab.book_id = b.id "
            + "INNER JOIN borrow_book bb ON bb.book_id = b.id "
            + "INNER JOIN member m ON m.id = bb.member_id "
            + "INNER JOIN author a ON ab.author_id = a.id "
            + "INNER JOIN publisher p ON b.publisher_id = p.id "
            + "WHERE lower(b.title) LIKE lower(CONCAT('%',:title,'%')) or lower(b.subject) LIKE lower(CONCAT('%',:title,'%'))"
            + "ORDER BY bb.id",
            nativeQuery = true)
    List<BorrowedBook> searchByTitle(String title);

    @Query(value = "SELECT COUNT(b.id) FROM BorrowBook b " +
            "WHERE b.createDate >= :monday AND b.createDate <= :sunday AND b.member = :member")
    int numberBorrowBookOfWeek(LocalDate monday, LocalDate sunday, Member member);

}
