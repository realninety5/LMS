package org.zeep.library.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zeep.library.DTO.BookDTO;
import org.zeep.library.domain.AuthorDomain.Request.AuthorGetRequest;
import org.zeep.library.domain.CatalogueDomain.Requests.*;
import org.zeep.library.domain.CatalogueDomain.Response.CatalogueResponse;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.*;
import org.zeep.library.repo.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class CatalogueServiceTest {

    @InjectMocks CatalogueService service;
    @Mock BookRepo bookRepo;
    @Mock AuthorRepo authorRepo;
    @Mock GenreRepo genreRepo;
    @Mock BookYearRepo yearRepo;
    @Mock BookDTO bookDTO;

    private final Author author = Author.builder().firstName("John").lastName("James").build();
    private final GenreModel genre = GenreModel.builder().genre(Genre.Art).build();
    private final BookByYear year = BookByYear.builder().year("1992").build();
    private final BookModel book = BookModel.builder().bookName("The Hobbit").id(UUID.randomUUID()).build();
    private final BookEditionModel edition = BookEditionModel.builder().publisher("Papachulo Inc").published("1992")
            .bookId(book.getId()).build();
    private final BookItemModel item = BookItemModel.builder().available(true).id(UUID.randomUUID()).build();
    private final Set<BookEditionModel> editions = new HashSet<>();
    private final Set<BookItemModel> items = new HashSet<>();
    private final Set<Author> authors = new HashSet<>();
    private final Set<BookModel> books = new HashSet<>();

    private final AuthorGetRequest authorRequest = AuthorGetRequest.builder().firstName("John").lastName("James")
            .build();
    private final BookByGenreRequest genreRequest = BookByGenreRequest.builder().genre(Genre.Art).build();
    private final BookByYearRequest yearRequest = BookByYearRequest.builder().year("1992").build();

    @BeforeEach
    void setUp() {
        items.add(item);
        editions.add(edition);
        authors.add(author);
        books.add(book);

        edition.setAllBooks(items);
        book.setEditions(editions);
        book.setAuthor(authors);
        author.setBooks(books);
        genre.setBooks(books);
        year.setBooks(editions);


        bookDTO = BookDTO.builder().bookId(item.getId()).bookName(book.getBookName()).published(edition.getPublished())
                        .author(book.getAuthor()).desc(book.getDesc()).genre(book.getGenre()).position(item.getPosition())
                        .reserved(item.isReserved()).available(item.isAvailable()).numOfPages(edition.getNumOfPages())
                        .edition(edition.getEdition()).publisher(edition.getPublisher()).build();


        lenient().when(authorRepo.findByFirstNameAndLastName("John", "James")).thenReturn(author);
        lenient().when(genreRepo.findByGenre(Genre.Art)).thenReturn(genre);
        lenient().when(yearRepo.findByYear("1992")).thenReturn(year);
        lenient().when(bookRepo.findById(edition.getBookId())).thenReturn(java.util.Optional.of(book));
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBooksByAuthor() {
        CatalogueResponse resp = service.getBooksByAuthor(authorRequest);
        assertNotNull(resp);
        assertNotNull(resp.getBooks().get(book.getBookName()));
        assertNotNull(resp.getBooks().get(book.getBookName()).stream().findFirst().get().getBookName());
    }

    @Test
    void getBookByYear() {
        CatalogueResponse resp = service.getBookByYear(yearRequest);
        assertNotNull(resp);
        assertNotNull(resp.getBooks().get(book.getBookName()));
        assertNotNull(resp.getBooks().get(book.getBookName()).stream().findFirst().get().getBookName());
    }

    @Test
    void getBookByGenre() {
        CatalogueResponse resp = service.getBookByGenre(genreRequest);
        assertNotNull(resp);
        assertNotNull(resp.getBooks().get(book.getBookName()));
        assertNotNull(resp.getBooks().get(book.getBookName()).stream().findFirst().get().getBookName());
    }
}