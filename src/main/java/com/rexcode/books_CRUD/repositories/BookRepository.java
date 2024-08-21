package com.rexcode.books_CRUD.repositories;


import com.rexcode.books_CRUD.domain.Book;
import com.rexcode.books_CRUD.domain.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BookRepository  extends JpaRepository<BookEntity, String>{

    Book save(Book book);

    
} 
