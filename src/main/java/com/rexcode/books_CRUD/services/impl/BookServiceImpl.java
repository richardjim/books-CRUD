
package com.rexcode.books_CRUD.services.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.rexcode.books_CRUD.domain.Book;
import com.rexcode.books_CRUD.domain.BookEntity;
// import com.rexcode.books_CRUD.domain.BookEntity.BookEntityBuilder;
import com.rexcode.books_CRUD.repositories.BookRepository;
import com.rexcode.books_CRUD.services.BookService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {

  private final BookRepository bookRepository;

  @Autowired
  public BookServiceImpl(final BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book save(Book book) {
    final BookEntity bookEntity = bookToBookEntity(book);
    final BookEntity saveBookEntity = bookRepository.save(bookEntity);
    return bookEntityToBook(saveBookEntity);
  }

  private BookEntity bookToBookEntity(Book book) {
    return BookEntity.builder()
        .isbn(book.getIsbn())
        .title(book.getTitle())
        .author(book.getAuthor())
        .build();
  }

  private Book bookEntityToBook(BookEntity bookEntity) {
    return Book.builder()
        .isbn(bookEntity.getIsbn())
        .title(bookEntity.getTitle())
        .author(bookEntity.getAuthor())
        .build();
  }

  @Override
  public Optional<Book> findById(String isbn) {
    final Optional<BookEntity> findBook = bookRepository.findById(isbn);
    return findBook.map(book -> bookEntityToBook(book));
  }

  @Override
  public List<Book> listBooks() {
    final List<BookEntity> foundBooks = bookRepository.findAll();
    return foundBooks.stream().map(book -> bookEntityToBook(book)).collect(Collectors.toList());

  }

  @Override
  public Boolean isBookExist(Book book) {
    return bookRepository.existsById(book.getIsbn());
  }

  @Override
  public void deleteBookById(String isbn) {
    try {
      bookRepository.deleteById(isbn);
    } catch (final EmptyResultDataAccessException e) {
      log.debug("Attempt to delete non-existing book", e);
    }

  }

}
