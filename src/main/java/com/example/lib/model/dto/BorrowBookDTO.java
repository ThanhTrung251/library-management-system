package com.example.lib.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BorrowBookDTO {
    private Long id;

    private Long bookID;

    private Long memberID;

    private LocalDate createDate;

    private LocalDate returnDate;

}

