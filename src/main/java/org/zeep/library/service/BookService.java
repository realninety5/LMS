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
import org.zeep.library.repo.BookEditionRepo;
import org.zeep.library.repo.BookItemRepo;
import org.zeep.library.repo.BookRepo;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
public class BookService {

    private final BookRepo repo;
    private final BookItemRepo itemRepo;
    private final BookEditionRepo editionRepo;
    @Autowired
    AuthorService service;
    static String position = "1000";
    public BookService(BookRepo repo, BookItemRepo itemRepo, BookEditionRepo editionRepo) {
        this.repo = repo;
        this.itemRepo = itemRepo;
        this.editionRepo = editionRepo;
    }


    public BookResponse addBook(BookAddRequest request) {
        Author author = service.create(request.getAuthor());

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

        BookModel book = BookModel.builder()
                .bookName(request.getBookName())
                .genre(request.getGenre())
                .author(author).desc(request.getDesc())
                .editions(Collections.singletonList(editionRepo.save(edition)))
                .build();

        return BookResponse.builder().body(repo.save(book)).responseCode(HttpStatus.CHECKPOINT.value())
                .message("Book added successfully.").build();
    }

    public void editBook() {

    }

    public void lendBook() {

    }

    public void returnBook() {

    }
}
