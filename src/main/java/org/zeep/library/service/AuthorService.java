package org.zeep.library.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeep.library.domain.AuthorDomain.Response.AuthorResponse;
import org.zeep.library.domain.AuthorDomain.Request.AuthorRequest;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookModel;
import org.zeep.library.repo.AuthorRepo;
import org.zeep.library.repo.BookRepo;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

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

    public Set<Author> create(Set<AuthorRequest> request) {
        Set<Author> authors = new HashSet<>();
        for (AuthorRequest fauthor: request) {
            Author author = repo.findByFirstNameAndLastName(fauthor.getFirstName(), fauthor.getLastName());
            if (author != null) {
                authors.add(author);
            } else {
                author = repo.save(Author.builder().id(UUID.randomUUID())
                        .firstName(fauthor.getFirstName())
                        .lastName(fauthor.getLastName())
                        .initial(fauthor.getInitial())
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
