package com.filipberski.readfile.services;

import com.filipberski.readfile.model.Book;

import java.util.List;

public interface BookService {

    void add (Book book);

    List<Book> findAll();

    Book getByIsbn (String isbn);

    List<Book> getByCategory (String category);

    List<Book> getRatingBooks();
}
