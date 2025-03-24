package org.himusharier.librarymanagementsystem.service;

import org.himusharier.librarymanagementsystem.dto.BookRequest;
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
    public Book addBook(BookRequest bookRequest) {
        Book book = Book.build(
                0,
                bookRequest.getName(),
                bookRequest.getAuthor(),
                bookRequest.getReleaseYear(),
                bookRequest.getIsbn(),
                bookRequest.getGenre()
        );
        return bookRepository.save(book);
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> checkBook = bookRepository.findById(book.getId());
        if (checkBook.isPresent()) {
            return bookRepository.save(book);
        }
        throw new BookNotFoundException("book not found with id: " + book.getId());
    }

    @Override
    public List<Book> searchBookByName(String keyword) {
        return bookRepository.findByNameLikeIgnoreCase(keyword);
    }
}
