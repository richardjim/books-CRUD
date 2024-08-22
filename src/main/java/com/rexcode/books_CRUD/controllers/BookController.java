package com.rexcode.books_CRUD.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rexcode.books_CRUD.domain.Book;
import com.rexcode.books_CRUD.services.BookService;

@RestController
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(final BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> createUpdateBook(
            @PathVariable final String isbn,
            @RequestBody final Book book) {
        book.setIsbn(isbn);
        final boolean isBookExist = bookService.isBookExist(book);
        final Book savBook = bookService.save(book);

        if (isBookExist) {
            return new ResponseEntity<Book>(savBook, HttpStatus.OK);
        } else {
            return new ResponseEntity<Book>(savBook, HttpStatus.CREATED);
        }

    }

    @GetMapping(path = "/books/{isbn}")
    public ResponseEntity<Book> retrieveBook(@PathVariable final String isbn) {
        final Optional<Book> foundBook = bookService.findById(isbn);
        return foundBook.map(book -> new ResponseEntity<Book>(book, HttpStatus.OK))
                .orElse(new ResponseEntity<Book>(HttpStatus.NOT_FOUND));

    }

    @GetMapping(path = "/books")
    public ResponseEntity<List<Book>> listBooks() {

        return new ResponseEntity<List<Book>>(bookService.listBooks(), HttpStatus.OK);

    }

    @DeleteMapping(path = "/books/{isbn}") 
        public ResponseEntity deleteBook(@PathVariable final String isbn) {
          bookService.deleteBookById(isbn);
          return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
}
