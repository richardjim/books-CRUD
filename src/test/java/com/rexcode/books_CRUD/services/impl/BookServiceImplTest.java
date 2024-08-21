package com.rexcode.books_CRUD.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rexcode.books_CRUD.TestData.testBook;
import static com.rexcode.books_CRUD.TestData.testBookEntity;

import com.rexcode.books_CRUD.domain.Book;
import com.rexcode.books_CRUD.domain.BookEntity;
import com.rexcode.books_CRUD.repositories.BookRepository;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl underTest;

    public void testThatBookIsSaved() {
        final Book book = testBook();

        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.save(eq(bookEntity))).thenReturn(bookEntity);

        final Book result = underTest.create(book);

        assertEquals(book, result);
    }

    @Test
    public void testFindByIdReturnsEmptyWhenNoBook() {
        final String isbn = "232434";
        when(bookRepository.findById((isbn))).thenReturn(Optional.empty());
        final Optional<Book> result = underTest.findById(isbn);

        assertEquals(Optional.empty(), result);
    }

    @Test
    public void testFindByIdReturnsBookWhenExist() {
        final Book book = testBook();
        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.findById((book.getIsbn()))).thenReturn(Optional.of(bookEntity));

        final Optional<Book> result = underTest.findById(book.getIsbn());
        assertEquals(Optional.of(book), result);
    }

}
