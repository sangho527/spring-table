package service;


import domain.entity.Author;
import domain.entity.Book;
import domain.dto.BookResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public List<BookResponse> findBooks(Pageable pageable) {
        Page<Book> books = bookRepository.findAll(pageable);
        List<BookResponse> bookResponses = books.stream()
                .map(book -> BookResponse.of(book)).collect(Collectors.toList());
        return bookResponses;
    }
}
