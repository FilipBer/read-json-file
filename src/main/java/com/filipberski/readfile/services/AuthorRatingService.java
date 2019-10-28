package com.filipberski.readfile.services;

import com.filipberski.readfile.model.AuthorRating;
import com.filipberski.readfile.model.Book;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorRatingService {
    private List<AuthorRating> authorRatings = new ArrayList<>();
    private BookServices bookServices;

    public AuthorRatingService(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    public List<AuthorRating> getRating () {
        int id;
        String[] authors;
        List<Book> ratingsBooks = bookServices.getRatingBooks();

        for(Book book : ratingsBooks) {
            authors = book.getAuthors();
            for (String author : authors) {
                id = findIdByAuthor(author);
                if (id != -1) {
                    this.authorRatings.get(id).addRating(book.getAverageRating());
                } else {
                    this.authorRatings.add(new AuthorRating(author, book.getAverageRating()));
                }
            }
        }
        if (this.authorRatings == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return this.authorRatings;
    }

    private int findIdByAuthor(String author) {
        for (int i=0;i < this.authorRatings.size(); i++){
            if (this.authorRatings.get(i).getName().equals(author)) {
                return i;
            }
        }
        return -1;
    }
}
