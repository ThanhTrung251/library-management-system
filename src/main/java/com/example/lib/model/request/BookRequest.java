package com.example.lib.model.request;

import com.example.lib.utils.enum_type.BookStatus;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class BookRequest implements Serializable {

    private String subject;

    private String title;

    @Min(value = 1, message = "Số trang sách phải lớn hơn 0")
    private int pages;

    @Min(value = 1, message = "Số quyển sách phải lớn hơn 0")
    private int numberOfBook;

    @Min(value = 1, message = " Số bản quyển phải lớn hơn 0")
    private int copyright;

    @Min(value = 0)
    private int edition;

    private String library;

    private BookStatus BookStatus;

    private LocalDate creatAt;

    private Long publisherId;

    private List<Long> authors;


}
