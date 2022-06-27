package com.example.lib.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.example.lib.utils.enum_type.BookStatus;
@JsonIgnoreProperties
public interface BookAfterFilter {
    Integer getBookId();

    String getBookSubject();

    String getBookTitle();

    String getAuthorName();

    String getPublisherName();

    Integer getCopyRight();

    Integer getEdition();

    Integer getPages();

    Integer getNumberOfBook();

    String getLibrary();

    BookStatus getBookStatus();
}
