package com.example.lib.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public interface BorrowedBook {
    Integer getBorrowBookID();

    String getName();

    Integer getBookId();

    String getBookSubject();

    String getBookTitle();

    String getAuthorName();

    String getPublisherName();

    Integer getCopyRight();

    Integer getEdition();

    Integer getPages();

    String getLibrary();
}
