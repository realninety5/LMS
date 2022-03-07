package org.zeep.library.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeep.library.domain.BookDomain.Requests.BookAddRequest;
import org.zeep.library.domain.BookDomain.Responses.BookResponse;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookEditionModel;
import org.zeep.library.model.BookItemModel;
import org.zeep.library.model.BookModel;
import org.zeep.library.repo.AuthorRepo;
import org.zeep.library.repo.BookEditionRepo;
import org.zeep.library.repo.BookItemRepo;
import org.zeep.library.repo.BookRepo;

import java.util.*;

@Service
public class BookService {

    private final BookRepo repo;
    private final BookItemRepo itemRepo;
    private final BookEditionRepo editionRepo;
    private final AuthorRepo authorRepo;
    @Autowired
    AuthorService service;
    static String position = "1000";
    public BookService(BookRepo repo, BookItemRepo itemRepo, BookEditionRepo editionRepo,
                       AuthorRepo authorRepo) {
        this.repo = repo;
        this.itemRepo = itemRepo;
        this.editionRepo = editionRepo;
        this.authorRepo = authorRepo;
    }


    public BookResponse addBook(BookAddRequest request) {

        BookItemModel item = BookItemModel.builder()
                .available(true).id(UUID.randomUUID())
                .reserved(false).dateAdded(new Date())
                .position(position).build();

        BookEditionModel edition = BookEditionModel.builder()
                .id(UUID.randomUUID())
                .publisher(request.getPublisher())
                .isbn(request.getIsbn())
                .numOfPages(request.getNumOfPages())
                .published(request.getPublished())
                .allBooks(Collections.singletonList(itemRepo.save(item)))
                .edition(request.getEdition())
                .build();


        Set<Author> authors = service.create(request.getAuthor());

        BookModel book = BookModel.builder()
                .bookName(request.getBookName())
                .genre(request.getGenre())
                .author(authors)
                .desc(request.getDesc())
                .editions(Collections.singletonList(editionRepo.save(edition)))
                .build();

        Set<BookModel> bookModelSet = new HashSet<>();
        bookModelSet.add(book);
        for (Author author: authors) {
            author.setBooks(bookModelSet);
        }

        return BookResponse.builder().body(repo.save(book)).responseCode(HttpStatus.CREATED.value())
                .message("Book added successfully.").build();
    }

    public void addEdition() {

    }

    public void editBook() {

    }

    public void lendBook() {

    }

    public void returnBook() {

    }
}
