package org.himusharier.librarymanagementsystem.service;

import org.himusharier.librarymanagementsystem.dto.BookAddRequest;
import org.himusharier.librarymanagementsystem.dto.BookUpdateRequest;
import org.himusharier.librarymanagementsystem.entity.Book;
import org.himusharier.librarymanagementsystem.exception.BookNotFoundException;
import org.himusharier.librarymanagementsystem.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(BookAddRequest bookAddRequest) {
        Book book = Book.builder()
                .name(bookAddRequest.getName())
                .author(bookAddRequest.getAuthor())
                .releaseYear(bookAddRequest.getReleaseYear())
                .isbn(bookAddRequest.getIsbn())
                .genre(bookAddRequest.getGenre())
                .build();
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(long id) {
        return bookRepository.getBookById(id);
    }

    @Override
    public Book updateBook(BookUpdateRequest bookUpdateRequest) {
        Optional<Book> checkBook = bookRepository.findById(bookUpdateRequest.getId());
        if (checkBook.isPresent()) {
            Book updatedBook = Book.builder()
                    .id(bookUpdateRequest.getId())
                    .name(bookUpdateRequest.getName())
                    .author(bookUpdateRequest.getAuthor())
                    .releaseYear(bookUpdateRequest.getReleaseYear())
                    .isbn(bookUpdateRequest.getIsbn())
                    .genre(bookUpdateRequest.getGenre())
                    .build();
            return bookRepository.save(updatedBook);

        } else {
            throw new BookNotFoundException("book not found with id: " + bookUpdateRequest.getId());
        }
    }

    @Override
    public boolean deleteBook(long id) {
        if (bookRepository.existsById(id)) {
            bookRepository.deleteById(id);
            return true;

        } else {
            return false;
        }
    }

    @Override
    public List<Book> searchBookByName(String keyword) {
        return bookRepository.findByNameContainingIgnoreCase(keyword);
    }
}
