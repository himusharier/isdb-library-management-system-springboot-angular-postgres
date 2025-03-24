package org.himusharier.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookUpdateRequest {
    @NotNull(message = "book id can not be null")
    private long id;

    @NotBlank(message = "book name can not be blank")
    private String name;

    @NotBlank(message = "book author can not be blank")
    private String author;

    private Date releaseYear;

    @NotBlank(message = "book isbn number can not be blank")
    private String isbn;

    private String genre;
}
