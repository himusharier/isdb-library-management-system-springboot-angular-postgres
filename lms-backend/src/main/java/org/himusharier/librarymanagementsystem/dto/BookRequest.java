package org.himusharier.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {

    @NotBlank(message = "book name can not be blank")
    private String name;

    @NotBlank(message = "book author can not be blank")
    private String author;

    private Date releaseYear;

    @NotBlank(message = "book isbn number can not be blank")
    private String isbn;

    private String genre;
}
