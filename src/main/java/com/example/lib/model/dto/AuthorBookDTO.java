package com.example.lib.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorBookDTO {
    private  Long id;

    private AuthorDTO authorDTO;

    private BookDTO bookDTO;
}
