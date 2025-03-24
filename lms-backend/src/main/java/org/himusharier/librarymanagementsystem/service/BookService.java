package org.himusharier.librarymanagementsystem.service;

import org.himusharier.librarymanagementsystem.dto.BookRequest;
import org.himusharier.librarymanagementsystem.entity.Book;

import java.util.List;

public interface BookService {
    public Book addBook(BookRequest bookRequest);
    public List<Book> getAllBooks();
    public Book updateBook(Book book);
    public List<Book> searchBookByName(String keyword);
}
