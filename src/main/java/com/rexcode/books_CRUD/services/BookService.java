package com.rexcode.books_CRUD.services;

import java.util.List;
import java.util.Optional;

import com.rexcode.books_CRUD.domain.Book;

public interface BookService {

        Book save(Book book);

         Boolean isBookExist(Book book);

        Optional<Book> findById(String isbn);

        List<Book> listBooks();

        void deleteBookById(String isbn);

    
    
}
