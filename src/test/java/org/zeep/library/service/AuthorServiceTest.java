package org.zeep.library.service;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zeep.library.domain.AuthorDomain.Request.AuthorGetRequest;
import org.zeep.library.domain.AuthorDomain.Response.AuthorResponse;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookModel;
import org.zeep.library.repo.AuthorRepo;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

//@SpringBootTest
@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

    @InjectMocks private AuthorService service;
    @Mock private AuthorRepo repo;
    private final Faker fk = new Faker(new Locale("en-US"));
    private final Set<BookModel> books = new HashSet<>();
    private final Author author = Author.builder().id(UUID.randomUUID()).lastName("Adichie")
            .firstName("Chimamanda").build();
    private final AuthorGetRequest request = AuthorGetRequest.builder().firstName("Chimamanda")
            .lastName("Adichie").build();

    @BeforeEach
    void setUp() {
        //service = new AuthorService(repo);
        books.add(new BookModel());
        books.add(new BookModel());
        lenient().when(this.repo.findByFirstNameAndLastName("Chimamanda", "Adichie"))
                .thenReturn(author);
        lenient().when(this.repo.save(any(Author.class))).thenReturn(author);

    }

    @AfterEach
    void tearDown() {
        repo.deleteAll();
    }

    @Test
    void getAuthor() {
        AuthorResponse res = this.service.getAuthor(request);
        assertEquals(200, res.getResponseCode());
    }

    @Test
    void create() {
        Set<AuthorGetRequest> oldAuthor = new HashSet<>();
        oldAuthor.add(AuthorGetRequest.builder().firstName("Chimamanda")
                .lastName("Adichie").build());

        Set<Author> authors = this.service.create(oldAuthor);
        for (Author author: authors) {
            assertEquals("Chimamanda", this.author.getFirstName());
        }
    }

    @Test
    void addBook() {
    }
}