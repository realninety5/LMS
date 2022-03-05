package org.zeep.library.service;


import org.springframework.stereotype.Service;
import org.zeep.library.domain.BookDomain.Requests.AuthorRequest;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookEditionModel;
import org.zeep.library.model.BookModel;
import org.zeep.library.repo.AuthorRepo;
import org.zeep.library.repo.BookRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorService {
    private final AuthorRepo repo;
    private final BookRepo bookRepo;

    public AuthorService(AuthorRepo repo, BookRepo bookRepo) {
        this.repo = repo;
        this.bookRepo = bookRepo;
    }

    public Author create(AuthorRequest request) {
        Author author = Author.builder().id(UUID.randomUUID())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .initial(request.getInitial())
                .build();
        return repo.save(author);
    }

    public Author addBook(BookModel book, AuthorRequest request) {
        Author author = repo.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        List<BookModel> books = author.getBooks();
        books.add(book);
        author.setBooks(books);
        return repo.save(author);
    }

//    public Author addEdition(BookEditionModel book, AuthorRequest request, UUID id) {
//        Author author = repo.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
//        BookModel book1 = author.getBook(id);
//
//        List<BookEditionModel> editions = book1.getEditions();
//        editions.add(book);
//        book1.setEditions(editions);
//
//    }
}
