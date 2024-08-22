package com.rexcode.books_CRUD.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
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

        final Book result = underTest.save(book);

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

    @Test
    public void testListReturnsEmptyListOrDontExist() {
        when(bookRepository.findAll()).thenReturn(new ArrayList<BookEntity>());

        final List<Book> result = underTest.listBooks();
        assertEquals(0, result.size());
    }

    @Test
    public void testListReturnsListWhenBooksExist() {
        final BookEntity bookEntity = testBookEntity();

        when(bookRepository.findAll()).thenReturn(List.of(bookEntity));

        final List<Book> result = underTest.listBooks();
        assertEquals(1, result.size());
    }

    @Test
    public void testBookReturnFalseWhenDoesntExist() {
        when(bookRepository.existsById(any())).thenReturn(false);
        final boolean result = underTest.isBookExist(testBook());
        assertEquals(false, result);

    }

    @Test
    public void testBookReturnTrueWhenExist() {
        when(bookRepository.existsById(any())).thenReturn(true);
        final boolean result = underTest.isBookExist(testBook());
        assertEquals(true, result);

    }

    @Test
    public void testDeleteBookDeletesBook() {
        final String isbn = "1234567";
        underTest.deleteBookById(isbn);
        verify(bookRepository, times(1)).deleteById(isbn);
    }


}
