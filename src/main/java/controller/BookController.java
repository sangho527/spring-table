package controller;

import domain.dto.BookResponse;
import domain.entity.Book;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.BookService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("") // 책 전체 목록
    public ResponseEntity<List<BookResponse>> list(Pageable pageable) {
        return ResponseEntity.ok().body(bookService.findBooks(pageable));
    }

    @GetMapping("/{id}") // 책 한개의 정보
    public ResponseEntity<Book> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(new Book());
    }
}
