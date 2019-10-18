package com.filipberski.readfile.services;

import com.filipberski.readfile.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServices {


    private List<Book> books = new ArrayList<>();

    public void add (Book book) {
        books.add(book);
    }

    public List<Book> findAll() {
        return books;
    }

    public Book getByIsbn (String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
