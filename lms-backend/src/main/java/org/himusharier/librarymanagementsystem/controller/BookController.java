package org.himusharier.librarymanagementsystem.controller;

import jakarta.validation.Valid;
import org.himusharier.librarymanagementsystem.dto.BookAddRequest;
import org.himusharier.librarymanagementsystem.dto.BookUpdateRequest;
import org.himusharier.librarymanagementsystem.entity.Book;
import org.himusharier.librarymanagementsystem.exception.BookAddingException;
import org.himusharier.librarymanagementsystem.exception.BookNotFoundException;
import org.himusharier.librarymanagementsystem.exception.DuplicateEntryException;
import org.himusharier.librarymanagementsystem.service.BookServiceImpl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookServiceImpl bookService;
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Map<String, Object>> addBook(@RequestBody @Valid BookAddRequest bookAddRequest) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Book addedBook = bookService.addBook(bookAddRequest);

            response.put("status", "success");
            response.put("code", HttpStatus.CREATED.value());
            response.put("message", "book added successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("a book already exists with isbn: " + bookAddRequest.getIsbn());

        } catch (RuntimeException e) {
            throw new BookAddingException("book adding failed");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllBooks() {
        Map<String, Object> response = new LinkedHashMap<>();
        List<Book> books = bookService.getAllBooks();

        response.put("status", "success");
        response.put("code", HttpStatus.OK.value());
        response.put("data", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        Book book = bookService.getBookById(id);

        if (book != null) {
            response.put("status", "success");
            response.put("code", HttpStatus.OK.value());
            response.put("data", book);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            throw new BookNotFoundException("book not found with the id: " + id);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Map<String, Object>> updateBook(@RequestBody @Valid BookUpdateRequest bookUpdateRequest) {
        Map<String, Object> response = new LinkedHashMap<>();
        try {
            Book updatedBook = bookService.updateBook(bookUpdateRequest);

            response.put("status", "success");
            response.put("code", HttpStatus.CREATED.value());
            response.put("message", "book updated successfully");
            return new ResponseEntity<>(response, HttpStatus.CREATED);

        } catch (DataIntegrityViolationException e) {
            throw new DuplicateEntryException("a book already exists with isbn: " + bookUpdateRequest.getIsbn());

        } catch (BookNotFoundException e) {
            throw new BookAddingException(e.getMessage());

        } catch (RuntimeException e) {
            throw new BookAddingException("book updating failed");
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Map<String, Object>> deleteBookById(@PathVariable long id) {
        Map<String, Object> response = new LinkedHashMap<>();
        boolean deletedBook = bookService.deleteBook(id);

        if (deletedBook) {
            response.put("status", "success");
            response.put("code", HttpStatus.OK.value());
            response.put("message", "book deleted successfully");
            return new ResponseEntity<>(response, HttpStatus.OK);

        } else {
            throw new BookNotFoundException("book not found with the id: " + id);
        }
    }

    @GetMapping("/search")
    public ResponseEntity<Map<String, Object>> searchByBookName(@RequestParam(name = "keyword", required = true) String keyword) {
        Map<String, Object> response = new LinkedHashMap<>();
        List<Book> books = bookService.searchBookByName(keyword.trim());

        response.put("status", "success");
        response.put("code", HttpStatus.OK.value());
        response.put("data", books);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
