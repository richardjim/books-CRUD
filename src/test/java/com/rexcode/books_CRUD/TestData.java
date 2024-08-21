package com.rexcode.books_CRUD;

import com.rexcode.books_CRUD.domain.Book;
import com.rexcode.books_CRUD.domain.BookEntity;

public class TestData {
 
    private TestData() {

    }

    public static Book testBook(){
        return Book.builder()
        .isbn("232e24e")
        .title("Mr")
        .author("Amos Brown")
        .build();    
    }

    public static BookEntity testBookEntity() {
       return  BookEntity.builder()
        .isbn("232e24e")
        .title("Mr")
        .author("Amos Brown")
        .build();
    }
    
}
