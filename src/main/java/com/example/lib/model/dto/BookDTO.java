package com.example.lib.model.dto;

import com.example.lib.utils.enum_type.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private Long id;

    private String subject;

    private String title;

    private int pages;

    private int numberOfBook;

    private int copyright;

    private int edition;

    private String library;

    private LocalDate creatAt;

    private BookStatus bookStatus;

    private Long publisherID;

    private List<Long> authorIds;

}
