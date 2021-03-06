package org.zeep.library.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeep.library.ExceptionsAndValidators.Exceptions.BookNotFoundException;
import org.zeep.library.domain.BookDomain.Requests.Manage.*;
import org.zeep.library.domain.BookDomain.Responses.BookResponse;
import org.zeep.library.model.*;
import org.zeep.library.repo.*;

import java.util.*;

@Service
public class BookManagementService {

    private final BookRepo repo;
    private final BookItemRepo itemRepo;
    private final BookEditionRepo editionRepo;
//    @Autowired
    private final AuthorService authorService;
//    @Autowired
    private final YearService yearService;
    private final GenreService genreService;
    static String position = "1000";

    public BookManagementService(BookRepo repo, BookItemRepo itemRepo, BookEditionRepo editionRepo, AuthorService authorService, YearService yearService, GenreService genreService) {
        this.repo = repo;
        this.itemRepo = itemRepo;
        this.editionRepo = editionRepo;
        this.authorService = authorService;
        this.yearService = yearService;
        this.genreService = genreService;
    }

    public BookResponse addBook(BookAddRequest request) {

        // Create the new book item
        BookItemModel item = BookItemModel.builder()
                .available(true)
                .reserved(false).dateAdded(new Date())
                .position(position).build();

        // Create the new edition for the book
        BookEditionModel edition = BookEditionModel.builder()
                .publisher(request.getPublisher())
                .isbn(request.getIsbn())
                .numOfPages(request.getNumOfPages())
                .published(request.getPublished())
                .edition(request.getEdition())
                .build();

        // set the year for the book, this makes retrieving the books by year easier
        edition.setYear(yearService.create(request.getPublished(), edition));

        // add the book item to the edition allBooks set
        Set<BookItemModel> items = new HashSet<>();
        items.add(itemRepo.save(item));
        edition.setAllBooks(items);

        // add the edition to the edition set of the book model
        Set<BookEditionModel> editions = new HashSet<>();
        editions.add(editionRepo.save(edition));

        // create the book object and
        // create a new set and add all the authors that contributed to this book
        Set<Author> authors = authorService.create(request.getAuthor());
        BookModel book = BookModel.builder()
                .bookName(request.getBookName())
                .author(authors)
                .desc(request.getDesc())
                .build();
        book.setEditions(editions);

        // sets the book's genre
        book.setGenre(genreService.create(request.getGenre(), book));
        BookModel newBook = repo.save(book);

        // sets the editionId of the book item for retrieval when a book is reserved
        item.setEditionId(edition.getId());
        // sets the bookId in the edition entity for easy retrieval for getBooksByYear
        edition.setBookId(book.getId());
        // this calls the author service to update each of the authors' book's set
        for (Author author: authors) {
            author.getBooks().add(newBook);
        }

        return BookResponse.builder().body(newBook).responseCode(HttpStatus.CREATED.value())
                .message("Book added successfully.").build();
    }

    public BookResponse getBook(UUID id) {
        // get a particular book and return it
        Optional<BookModel> book = repo.findById(id);
        BookModel book1 = null;
        if (book.isPresent()) {
            book1 = book.get();
        } else {
            return BookResponse.builder().body(book1).message("No such book, dude.")
                    .responseCode(HttpStatus.NOT_FOUND.value()).build();
        }
        return BookResponse.builder().body(book1).message("Have it boy.")
                .responseCode(HttpStatus.OK.value()).build();
    }

    public BookResponse addEdition(EditionAddRequest request) {

        Optional<BookModel> book1 = repo.findById(request.getBookId());
        BookModel book;
        if (book1.isPresent()) {
            book = book1.get();
        } else {
            return BookResponse.builder().body(null).responseCode(HttpStatus.NOT_FOUND.value())
                    .message("No such book, add the book first.").build();
        }

        // Create a book item object
        BookItemModel item = BookItemModel.builder()
                .available(true)
                .reserved(false).dateAdded(new Date())
                .position(position).build();

        // create a book edition object
        BookEditionModel edition = BookEditionModel.builder()
                .publisher(request.getPublisher())
                .isbn(request.getIsbn())
                .numOfPages(request.getNumOfPages())
                .published(request.getPublished())
                .edition(request.getEdition())
                .bookId(book.getId())
                .allBooks(new HashSet<>())
                .build();

        // set the year of this particular edition for easier retrieval
        // this way, we have the edition in our db and have access to the main book
        edition.setYear(yearService.create(request.getPublished(), edition));
        edition.getAllBooks().add(itemRepo.save(item));

        book.getEditions().add(editionRepo.save(edition));
        // sets the editionId of the book item for retrieval when a book is reserved
        item.setEditionId(edition.getId());
        // sets the bookId in the edition entity for easy retrieval for getBooksByYear
        edition.setBookId(book.getId());

        return BookResponse.builder().body(book).responseCode(HttpStatus.CREATED.value())
                .message("Book added successfully.").build();
    }

    public BookResponse addItem(ItemAddRequest request) {

        Optional<BookEditionModel> edition1 = editionRepo.findById(request.getEditionId());
        BookEditionModel edition;
        if (edition1.isPresent()) {
            edition = edition1.get();
        } else {
            return BookResponse.builder().body(null).responseCode(HttpStatus.NOT_FOUND.value())
                    .message("No such edition, add the edition first.").build();
        }

        BookItemModel item = BookItemModel.builder().position("1002")
                .available(true).dateAdded(new Date()).reserved(false)
                .build();

        edition.getAllBooks().add(itemRepo.save(item));

        // sets the editionId of the book item for retrieval when a book is reserved
        item.setEditionId(edition.getId());

        return BookResponse.builder().body(null).responseCode(HttpStatus.CREATED.value())
                .message("Book has been added to the library.").build();
    }

    public BookResponse removeBookItem(BookRemoveRequest request) {
        BookItemModel model;
        try {
            model = itemRepo.getById(request.getBookId());
        } catch (RuntimeException e) {
            throw new BookNotFoundException("Book not found.");
        }
        itemRepo.delete(model);
        return BookResponse.builder().body(null).responseCode(HttpStatus.OK.value())
                .message("Book has been deleted from the library.").build();

    }

    public void editBook() {

    }

    public void lendBook() {

    }

    public void returnBook() {

    }
}
