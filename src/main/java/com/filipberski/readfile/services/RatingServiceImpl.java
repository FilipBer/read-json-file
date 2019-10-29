package com.filipberski.readfile.services;

import com.filipberski.readfile.model.Rating;
import com.filipberski.readfile.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {
    private List<Rating> ratings = new ArrayList<>();
    private BookService bookService;

    public RatingServiceImpl(BookService bookService) {
        this.bookService = bookService;
    }

    public List<Rating> getRating () {
        int id;
        String[] authors;
        List<Book> ratingsBooks = bookService.getRatingBooks();

        for(Book book : ratingsBooks) {
            authors = book.getAuthors();
            for (String author : authors) {
                id = findIdByAuthor(author);
                if (id != -1) {
                    this.ratings.get(id).addRating(book.getAverageRating());
                } else {
                    this.ratings.add(new Rating(author, book.getAverageRating()));
                }
            }
        }
        if (this.ratings == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.ratings;
    }

    public int findIdByAuthor(String author) {
        for (int i = 0; i < this.ratings.size(); i++){
            if (this.ratings.get(i).getName().equals(author)) {
                return i;
            }
        }
        return -1;
    }
}
