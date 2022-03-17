package org.zeep.library.repo;


import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.model.*;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookManagementTest {

    @Mock BookRepo bookRepo;
    @Mock BookItemRepo itemRepo;
    @Mock BookEditionRepo editionRepo;

    Set<Author> aa = new HashSet<>();
    private final BookModel book = BookModel.builder().id(UUID.randomUUID())
            .genre(new GenreModel())
            .bookName("To Kill A Mockingbird").build();
    private final BookEditionModel edition = BookEditionModel.builder().barcode(null).id(UUID.randomUUID())
            .numOfPages(300).isbn("QW1276736739").publisher("Jeho Pub").build();
    private final BookItemModel item = BookItemModel.builder().id(UUID.randomUUID()).available(true)
            .borrowedBy(null).borrowedDate(null).position("1001").build();

    @BeforeEach
    void setUp() {
        Set<Author> authors = new HashSet<>();
        authors.add(new Author());
        authors.add(new Author());
        book.setAuthor(authors);
        lenient().when(bookRepo.save(book)).thenReturn(book);
        lenient().when(editionRepo.save(edition)).thenReturn(edition);
        lenient().when(itemRepo.save(item)).thenReturn(item);
        lenient().when(bookRepo.findByBookName("To Kill A Mockingbird")).thenReturn(book);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("Should save books and checks if they are available.")
    void save() {
        Set<BookItemModel> items = new HashSet<>();
        items.add(item);
        edition.setAllBooks(items);
        Set<BookEditionModel> editions = new HashSet<>();
        editions.add(edition);
        book.setEditions(editions);
        BookModel b = bookRepo.save(book);
        assertNotNull(b);
        Optional<BookEditionModel> edd = b.getEditions().stream()
                .filter(ed -> ed.getIsbn().equalsIgnoreCase("QW1276736739")).findFirst();
        assertEquals("QW1276736739", edd.get().getIsbn());
    }

    @Test @DisplayName("Get a book from the db.")
    void findBookByName() {

        Set<BookItemModel> items = new HashSet<>();
        items.add(item);
        edition.setAllBooks(items);
        Set<BookEditionModel> editions = new HashSet<>();
        editions.add(edition);
        book.setEditions(editions);
        BookModel b = bookRepo.findByBookName("To Kill A Mockingbird");

        assertNotNull(b);
        assertEquals("To Kill A Mockingbird", b.getBookName());
    }

    @Test
    public void whenBothifyCalled_checkPatternMatches() throws Exception {

        FakeValuesService fvs = new FakeValuesService(
                new Locale("en-GB"), new RandomService());

        String email = fvs.bothify("????##@gmail.com");
        Matcher emailMatcher = Pattern.compile("\\w{4}@gmail.com").matcher(email);

        System.out.println(email);
        assertTrue(emailMatcher.find());

        Faker fk = new Faker(new Locale("en-GB"));
        System.out.println(fk.address().buildingNumber());
        System.out.println(fk.address().streetName());
        System.out.println(fk.address().city());
        System.out.println(fk.address().country());
        System.out.println(fk.name().firstName());
        System.out.println(fk.name().lastName());
        System.out.println(fk.regexify("\\w{8}\\d{2}")+"@gmail.com");
        System.out.println(fk.address().country());
    }
}