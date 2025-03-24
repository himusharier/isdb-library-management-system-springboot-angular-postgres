package org.himusharier.librarymanagementsystem.controller;

import jakarta.validation.Valid;
import org.himusharier.librarymanagementsystem.dto.BookRequest;
import org.himusharier.librarymanagementsystem.entity.Book;
import org.himusharier.librarymanagementsystem.exception.BookAddingException;
import org.himusharier.librarymanagementsystem.exception.DuplicateEntryException;
import org.himusharier.librarymanagementsystem.service.BookServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookServiceImpl bookService;
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody @Valid BookRequest bookRequest) {
        Map<String, Object> response = new HashMap<>();
        try {
            Book addedBook = bookService.addBook(bookRequest);
            response.put("code", HttpStatus.CREATED.value());
            response.put("message", "book added successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("a book already exists with isbn: " + bookRequest.getIsbn());

        } catch (RuntimeException e) {
            throw new BookAddingException("book adding failed");
        }
    }
}
