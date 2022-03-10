package org.zeep.library.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeep.library.domain.AuthorDomain.Response.AuthorResponse;
import org.zeep.library.domain.AuthorDomain.Request.AuthorRequest;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookModel;
import org.zeep.library.repo.AuthorRepo;
import org.zeep.library.repo.BookRepo;

import java.util.*;

@Service
public class AuthorService {
    private final AuthorRepo repo;
    private final BookRepo bookRepo;

    public AuthorService(AuthorRepo repo, BookRepo bookRepo) {
        this.repo = repo;
        this.bookRepo = bookRepo;
    }

    public AuthorResponse getAuthor() {
        Author author = repo.findByFirstNameAndLastName("Chimamanda", "Adichie");
        return AuthorResponse.builder().body(author).responseCode(HttpStatus.OK.value())
                .message("Here it is").build();
    }

    public Set<Author> create(Set<AuthorRequest> theAuthors) {
        Set<Author> authors = new HashSet<>();
        // loop through the provided authors, get their books and add this book to it
        // if the author does not exist, create a new one and add the books
        for (AuthorRequest fauthor: theAuthors) {
            Author author = repo.findByFirstNameAndLastName(fauthor.getFirstName(), fauthor.getLastName());
            if (author != null) {
                authors.add(author);
            } else {
                author = repo.save(Author.builder()
                        .firstName(fauthor.getFirstName())
                        .lastName(fauthor.getLastName())
                        .initial(fauthor.getInitial())
                        .books(new HashSet<>())
                        .build());
                authors.add(author);
            }
        }
        return authors;
    }

    public Author addBook(BookModel book, AuthorRequest request) {
        return new Author();
    }
}
