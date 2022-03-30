package org.zeep.library.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zeep.library.domain.AuthorDomain.Request.AuthorGetRequest;
import org.zeep.library.domain.BookDomain.Requests.Manage.*;
import org.zeep.library.domain.BookDomain.Responses.BookResponse;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.*;
import org.zeep.library.repo.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class BookManagementServiceTest {

    // Injects the needed services
    @InjectMocks private BookManagementService service;


    // Mock the needed repos
    @Mock private BookItemRepo repoItem;
    @Mock private BookEditionRepo repoEdition;
    @Mock private BookRepo repo;
    @Mock private AuthorService authorService;
    @Mock private YearService yearService;
    @Mock private GenreService genreService;

    // define some variables and assign values to them
    private final BookItemModel item = new BookItemModel();
    private final Set<BookItemModel> items = new HashSet<>();
    private final Set<BookEditionModel> editions = new HashSet<>();

    // define the needed sets
    private final Set<BookModel> books = new HashSet<>();
    private final Set<Author> authors = new HashSet<>();
    private final Set<AuthorGetRequest> authorRequests = new HashSet<>();


    // year, genre, and author
    private final BookByYear year = BookByYear.builder().year("2006").id(1L).build();
    private final GenreModel genre = GenreModel.builder().id(1L).genre(Genre.Art).build();
    private final Author author = Author.builder().firstName("Chimamanda").lastName("Adichie")
            .build();
    // build the book, edition models
    private final BookModel book = BookModel.builder().id(UUID.randomUUID()).desc("The Best")
            .bookName("Best").genre(genre).build();
    private final BookEditionModel edition = BookEditionModel.builder().edition("First Edition")
            .id(UUID.randomUUID()).isbn("1234567890").numOfPages(450).publisher("Kachifo Inc")
            .published("2006").bookId(book.getId()).year(year).build();

    // create the book request that the book service receives
    private final BookAddRequest request = BookAddRequest.builder().bookName("Best").genre(Genre.Art)
            .isbn("1234567890").numOfPages(450).publisher("Kachifo").published("2006").edition("First Edition")
            .desc("The Best").build();

    private final BookGetRequest requestGet = BookGetRequest.builder().bookId(UUID.randomUUID()).build();
    private final EditionAddRequest requestEditionAdd = EditionAddRequest.builder().edition("First Edition")
            .isbn("1234567890").numOfPages(450).publisher("Al Choco Inc").bookId(UUID.randomUUID()).build();
    private final Optional<BookModel> bookModel = Optional.of(book);
    private final Optional<BookEditionModel> editionModel = Optional.of(edition);
    private final ItemAddRequest addRequest = ItemAddRequest.builder().editionId(edition.getId()).build();


    @BeforeEach
    void setUp() {
        // instantiate the injected services

        this.authors.add(author);
        this.authorRequests.add(AuthorGetRequest.builder().firstName("Chimamanda").lastName("Adichie").build());
        this.items.add(item);
        this.editions.add(edition);
        this.books.add(book);


        // set their values
        this.book.setAuthor(authors);
        this.book.setEditions(editions);

        this.year.setBooks(editions);
        this.author.setBooks(books);
        this.genre.setBooks(books);

        this.request.setAuthor(authorRequests);
        this.edition.setAllBooks(items);

        lenient().when(this.repoItem.save(any(BookItemModel.class))).thenReturn(item);

        lenient().when(this.repoEdition.save(any(BookEditionModel.class))).thenReturn(edition);
        lenient().when(this.repo.save(any(BookModel.class))).thenReturn(book);
        lenient().when(this.repo.findById(requestGet.getBookId())).thenReturn(bookModel);

        lenient().when(this.repo.findById(requestEditionAdd.getBookId())).thenReturn(bookModel);
        lenient().when(this.repoEdition.findById(addRequest.getEditionId())).thenReturn(editionModel);

        lenient().when(this.yearService.create("2006", edition)).thenReturn(year);
        lenient().when(this.authorService.create(authorRequests)).thenReturn(authors);
        lenient().when(this.genreService.create(Genre.Art, book)).thenReturn(genre);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addBook() {
        BookResponse response = this.service.addBook(request);
        assertEquals(201, response.getResponseCode());
        assertEquals(book.getBookName(), response.getBody().getBookName());
    }

    @Test
    void getBook() {
        BookResponse response = this.service.getBook(requestGet.getBookId());
        assertEquals(200, response.getResponseCode());
        assertEquals(book.getBookName(), response.getBody().getBookName());
    }

    @Test
    void addEdition() {
        BookResponse response = this.service.addEdition(requestEditionAdd);
        assertEquals(201, response.getResponseCode());
    }

    @Test
    void addItem() {
        BookResponse response = this.service.addItem(addRequest);
        assertEquals(201, response.getResponseCode());
    }

    @Test
    void removeBookItem() {
    }

    @Test
    void editBook() {
    }

    @Test
    void lendBook() {
    }

    @Test
    void returnBook() {
    }
}