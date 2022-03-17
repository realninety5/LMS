package org.zeep.library.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.domain.AuthorDomain.Request.AuthorRequest;
import org.zeep.library.domain.BookDomain.Requests.Manage.BookAddRequest;
import org.zeep.library.domain.BookDomain.Responses.BookResponse;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.*;
import org.zeep.library.repo.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class BookManagementServiceTest {

    // Injects the needed services
    @InjectMocks private BookManagementService service;
    @InjectMocks private YearService yearService;
    @InjectMocks private GenreService genreService;
    @InjectMocks private AuthorService authorService;

    // Mock the needed repos
    @Mock private BookItemRepo repoItem;
    @Mock private BookEditionRepo repoEdition;
    @Mock private BookRepo repo;
    // External services
    @Mock private BookYearRepo yearRepo;
    @Mock private GenreRepo genreRepo;
    @Mock private AuthorRepo authorRepo;

    // define some variables and assign values to them
    private final BookItemModel item = new BookItemModel();
    private final Set<BookItemModel> items = new HashSet<>();
    private final Set<BookEditionModel> editions = new HashSet<>();

    private final Set<Author> authors = new HashSet<>();
    // year, genre, and author
    private final BookByYear year = BookByYear.builder().year("2006").id(1L).build();
    private final GenreModel genre = GenreModel.builder().id(1L).genre(Genre.Art).build();
    private final Author author = Author.builder().firstName("Chimamanda").lastName("Adichie")
            .build();
    // build the book, edition models
    private final BookModel model = BookModel.builder().id(UUID.randomUUID()).desc("The Best")
            .bookName("Best").genre(genre).build();
    private final BookEditionModel edition = BookEditionModel.builder().edition("First Edition")
            .id(UUID.randomUUID()).isbn("1234567890").numOfPages(450).publisher("Kachifo Inc")
            .published("2006").bookId(model.getId()).year(year).build();

    // create the book request that the book service receives
    private final BookAddRequest request = BookAddRequest.builder().bookName("Best").genre(Genre.Art)
            .isbn("1234567890").numOfPages(450).publisher("Kachifo").published("2006").edition("First Edition")
            .desc("The Best").build();


    @BeforeEach
    void setUp() {
        service = new BookManagementService(repo, repoItem, repoEdition);
        yearService = new YearService(yearRepo);
//        System.out.println(yearService);
//        System.out.println(service);
        authors.add(author);
        model.setAuthor(authors);
        year.setBooks(editions);
        Set<AuthorRequest> requests = new HashSet<>();
        requests.add(AuthorRequest.builder().firstName("Chimamanda").lastName("Adichie").build());
        request.setAuthor(requests);
        items.add(item);
        edition.setAllBooks(items);
        editions.add(edition);
        model.setEditions(editions);
        System.out.println(repo.count());
        lenient().when(yearRepo.findByYear("2006")).thenReturn(year);
        lenient().when(yearRepo.save(year)).thenReturn(year);
        System.out.println(yearRepo.count());
        lenient().when(repoItem.save(item)).thenReturn(item);

        lenient().when(repoEdition.save(edition)).thenReturn(edition);
        lenient().when(repo.save(model)).thenReturn(model);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addBook() {
        System.out.println(yearService);

        BookResponse response = service.addBook(request);
        assertEquals(201, response.getResponseCode());
    }

    @Test
    void getBook() {
    }

    @Test
    void addEdition() {
    }

    @Test
    void addItem() {
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