package com.filipberski.readfile.controllers;

import com.filipberski.readfile.model.Book;
import com.filipberski.readfile.services.BookServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private BookServices bookServices;

    public BookController(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        return new ResponseEntity<>(bookServices.findAll(),HttpStatus.OK);
    }

    @GetMapping("/book/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable("isbn") String isbn) throws ResponseStatusException {
        return new ResponseEntity<>(bookServices.getByIsbn(isbn), HttpStatus.OK);
    }
}
