package com.rexcode.books_CRUD.services;

import java.util.Optional;

import com.rexcode.books_CRUD.domain.Book;

public interface BookService {

        Book create(Book book);

        Optional<Book> findById(String isbn);

    
    
}
