package com.filipberski.readfile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.filipberski.readfile.model.Book;
import com.filipberski.readfile.services.BookServices;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${dataSource}")
    private String dataSource;

    private final BookServices bookServices;

    public DataLoader(BookServices bookServices) {
        this.bookServices = bookServices;
    }

    @Override
    public void run(String... args) throws Exception {

        loadData();
    }

    private void loadData() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        String source = "src/main/resources/static/" + dataSource;

        JsonNode json = objectMapper.readTree(new File(source));
        JsonNode jsonBooks = json.get("items");
        for (int i = 0; i < jsonBooks.size(); i++) {
            bookServices.add(readBook(jsonBooks.get(i)));
        }


    }

    private Book readBook(JsonNode jsonBook) {
        Book book = new Book();
        JsonNode authors;
        String[] authorsArray;
        String[] categoriesArray;
        JsonNode categories;
        LocalDateTime date;


        JsonNode volumeInfo = jsonBook.get("volumeInfo");
        if (volumeInfo.has("isbn")) {
            book.setIsbn(volumeInfo.get("isbn").asText());
        }
        if (volumeInfo.has("title")) {
            book.setTitle(volumeInfo.get("title").asText());
        }
        if (volumeInfo.has("subtitle")) {
            book.setSubtitle(volumeInfo.get("subtitle").asText());
        }
        if (volumeInfo.has("publisher")) {
            book.setPublisher(volumeInfo.get("publisher").asText());
        }
        if (volumeInfo.has("publishedDate")) {
            if (volumeInfo.get("publishedDate").asText().contains("-")){
                date = LocalDate.parse(volumeInfo.get("publishedDate").asText()).atStartOfDay();
            } else {
                date = LocalDate.parse(volumeInfo.get("publishedDate").asText() + "-01-01").atStartOfDay();
            }
            book.setPublishedDate(Timestamp.valueOf(date).getTime());
        }
        if (volumeInfo.has("description")) {
            book.setDescription(volumeInfo.get("description").asText());
        }
        if (volumeInfo.has("pageCount")) {
            book.setPageCount(volumeInfo.get("pageCount").asInt());
        }
        if (volumeInfo.has("imageLinks")) {
            if (volumeInfo.get("imageLinks").has("thumbnail")) {
                book.setThumbnailUrl(volumeInfo.get("imageLinks").get("thumbnail").asText());
            }
        }
        if (volumeInfo.has("language")) {
            book.setLanguage(volumeInfo.get("language").asText());
        }
        if (volumeInfo.has("previewLink")) {
            book.setPreviewLink(volumeInfo.get("previewLink").asText());
        }
        if (volumeInfo.has("averageRating")) {
            book.setAverageRating(volumeInfo.get("averageRating").asDouble());
        }
        JsonNode isb = volumeInfo.get("industryIdentifiers");
        for (JsonNode isbn : isb) {
            if (isbn.get("type").asText().equals("ISBN_13")) {
                book.setIsbn(isbn.get("identifier").asText());
            }
        }
        if (book.getIsbn() == null) {
            book.setIsbn(jsonBook.get("id").asText());
        }
        authors = volumeInfo.get("authors");
        if (authors != null) {
            authorsArray = new String[authors.size()];
            for (int i = 0; i < authors.size(); i++) {
                authorsArray[i] = authors.get(i).asText();
            }
            book.setAuthors(authorsArray);
        }
        categories = volumeInfo.get("categories");
        if (categories != null) {
            categoriesArray = new String[categories.size()];
            for (int i = 0; i < categories.size(); i++) {
                categoriesArray[i] = categories.get(i).asText();
            }
            book.setCategories(categoriesArray);
        }

        return book;
    }
}
