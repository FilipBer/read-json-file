package com.filipberski.readfile.services;

import com.filipberski.readfile.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {


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

    public List<Book> getByCategory (String category) {
        List<Book> searchedBooks = new ArrayList<>();
        String[] categories;
        for (Book book : books) {
            if(book.getCategories() != null) {
                categories = book.getCategories();
                for (String current : categories) {
                    if (current.equals(category)) {
                        searchedBooks.add(book);
                        break;
                    }
                }
            }
        }
        if (searchedBooks.size() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return searchedBooks;
    }

    public List<Book> getRatingBooks() {
        List<Book> ratingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getAverageRating() != null) {
                ratingBooks.add(book);
            }
        }
        return ratingBooks;
    }
}
