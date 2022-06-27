package com.example.lib.repository;

import com.example.lib.model.dto.BookAfterFilter;
import com.example.lib.model.entity.Book;
import com.example.lib.utils.enum_type.BookStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "SELECT b.id AS bookId, b.subject AS bookSubject, b.title AS bookTitle," +
            "              b.copyright AS copyright, b.edition AS edition, b.pages AS pages," +
            "              b.number_of_book AS numberofbook, b.library AS library, b.status AS bookStatus," +
            "              p.name AS publisherName, " +
            "              array_to_string(array( SELECT a.name FROM author a INNER JOIN author_book ab ON ab.author_id = a.id" +
            "                                     WHERE ab.book_id = b.id ),' - ') AS authorname" +
            "       FROM book b" +
            "       INNER JOIN publisher p ON b.publisher_id = p.id",
            nativeQuery = true)
    List<BookAfterFilter> listAllBook();

    @Query(value = "SELECT b.id AS bookId, b.subject AS bookSubject, b.title AS bookTitle," +
            "              b.copyright AS copyright, b.edition AS edition, b.pages AS pages," +
            "              b.number_of_book AS numberofbook, b.library AS library, b.status AS bookStatus," +
            "              p.name AS publisherName, " +
            "              array_to_string(array( SELECT a.name FROM author a INNER JOIN author_book ab ON ab.author_id = a.id" +
            "                                     WHERE ab.book_id = b.id ),' - ') AS authorname" +
            "       FROM book b" +
            "       INNER JOIN publisher p ON b.publisher_id = p.id" +
            "       WHERE (lower (b.subject) LIKE LOWER (concat('%',:keyword,'%')) OR LOWER (b.title) LIKE LOWER (CONCAT('%',:keyword,'%')) )" +
            "               AND (:edition = 0 OR b.edition = (:edition))" +
            "               AND ((b.status = :available) OR (b.status = :borrow) OR (b.status = :reserve))"
            , nativeQuery = true)
    List<BookAfterFilter> searchBook(String keyword, String available, String borrow, String reserve, Integer edition);

    List<Book> findAllByBookStatus(BookStatus bookStatus);
}
