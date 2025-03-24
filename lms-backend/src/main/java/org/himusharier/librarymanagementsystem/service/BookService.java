package org.himusharier.librarymanagementsystem.service;

import org.himusharier.librarymanagementsystem.dto.BookAddRequest;
import org.himusharier.librarymanagementsystem.dto.BookUpdateRequest;
import org.himusharier.librarymanagementsystem.entity.Book;

import java.util.List;

public interface BookService {
    Book addBook(BookAddRequest bookAddRequest);
    List<Book> getAllBooks();
    Book getBookById(long id);
    Book updateBook(BookUpdateRequest bookUpdateRequest);
    boolean deleteBook(long id);
    List<Book> searchBookByName(String keyword);
}
