package com.example.lib.model.entity;

import com.example.lib.utils.enum_type.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String subject;

    private String title;

    private int pages;

    private int numberOfBook;

    private int copyright;

    private int edition;

    private String library;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private BookStatus bookStatus;

    private LocalDate creatAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

    @OneToMany(mappedBy = "book")
    private List<BorrowBook> borrowBookList;

    @OneToMany(mappedBy = "book")
    private List<AuthorBook> authorBookList;

}

