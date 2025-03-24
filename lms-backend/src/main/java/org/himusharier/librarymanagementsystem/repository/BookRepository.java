package org.himusharier.librarymanagementsystem.repository;

import jakarta.validation.constraints.NotBlank;
import org.himusharier.librarymanagementsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByNameLikeIgnoreCase(String name);

}
