package com.rexcode.books_CRUD.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    public ResponseEntity<Book> createBook (
        @PathVariable final String isbn,
        @RequestBody final Book book) {
         book.setIsbn(isbn);
         final Book savBook = bookService.create(book);
         final ResponseEntity<Book> response = new ResponseEntity<Book>(savBook,HttpStatus.CREATED);
         return response;
        }
}
