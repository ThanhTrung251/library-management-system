package com.example.lib.model.request;

import com.example.lib.model.entity.Author;
import com.example.lib.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorBookRequest implements Serializable {
    private Author author;

    private Book book;
}
