package org.zeep.library.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeep.library.ExceptionsAndValidators.Exceptions.NotFoundException;
import org.zeep.library.domain.AuthorDomain.Response.AuthorResponse;
import org.zeep.library.domain.AuthorDomain.Request.AuthorGetRequest;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookModel;
import org.zeep.library.repo.AuthorRepo;

import java.util.*;

@Service
public class AuthorService {
    private final AuthorRepo repo;

    public AuthorService(AuthorRepo repo) {
        this.repo = repo;
    }

    public AuthorResponse getAuthor(AuthorGetRequest request) {
        Author author = repo.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        if (author == null) {
            throw new NotFoundException("Author not found.");
        }
        return AuthorResponse.builder().body(author).responseCode(HttpStatus.OK.value())
                .message("Here it is").build();
    }

    public Set<Author> create(Set<AuthorGetRequest> theAuthors) {
        Set<Author> authors = new HashSet<>();
        // loop through the provided authors, get their books and add this book to it
        // if the author does not exist, create a new one and add the books
        for (AuthorGetRequest newAuthor: theAuthors) {
            Author author = repo.findByFirstNameAndLastName(newAuthor.getFirstName(), newAuthor.getLastName());
            if (author != null) {
                authors.add(author);
            } else {
                // this means that we are only creating a new author only when we are adding a book
                // and can't just create a new author.
                author = repo.save(Author.builder()
                        .firstName(newAuthor.getFirstName())
                        .lastName(newAuthor.getLastName())
                        .initial(newAuthor.getInitial())
                        .books(new HashSet<>())
                        .build());
                authors.add(author);
            }
        }
        return authors;
    }

//    public Author addBook(BookModel book, AuthorGetRequest request) { return new Author(); }
}
