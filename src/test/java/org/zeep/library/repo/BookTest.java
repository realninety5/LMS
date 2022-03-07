//package org.zeep.library.repo;
//
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.annotation.SecurityTestExecutionListeners;
//import org.zeep.library.enums.Genre;
//import org.zeep.library.model.*;
//
//
//import java.util.Collections;
//import java.util.HashSet;
//import java.util.Set;
//import java.util.UUID;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.lenient;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//class BookTest {
//
//    @Mock BookRepo bookRepo;
//    @Mock BookItemRepo itemRepo;
//    @Mock BookEditionRepo editionRepo;
//
//    Set<Author> aa = new HashSet<>();
//    private final BookModel book = BookModel.builder().id(UUID.randomUUID()).genre(Genre.Art)
//            .author(Collections.singletonList(new Author()))
//            .bookName("To Kill A Mockingbird").build();
//    private final BookEditionModel edition = BookEditionModel.builder().barcode(null).id(UUID.randomUUID())
//            .numOfPages(300).isbn("QW1276736739").publisher("Jeho Pub").build();
//    private final BookItemModel item = BookItemModel.builder().id(UUID.randomUUID()).available(true)
//            .borrowedBy(null).borrowedDate(null).position("1001").build();
////    private final BookEditionModel bookEdition = BookEditionModel.builder().
//
//    @BeforeEach
//    void setUp() {
//        lenient().when(bookRepo.save(book)).thenReturn(book);
//        lenient().when(editionRepo.save(edition)).thenReturn(edition);
//        lenient().when(itemRepo.save(item)).thenReturn(item);
//        lenient().when(bookRepo.findByBookName("To Kill A Mockingbird")).thenReturn(book);
//    }
//
//    @AfterEach
//    void tearDown() {
//    }
//
//    @Test
//    @DisplayName("Should save books and checks if they are available.")
//    void save() {
//        edition.setAllBooks(Collections.singletonList(item));
//        book.setEditions(Collections.singletonList(edition));
//        BookModel b = bookRepo.save(book);
//        assertNotNull(b);
//        assertEquals("Sti-doine", b.getEditions().get(0).getAllBooks().get(0).getPosition());
//    }
//
//    @Test @DisplayName("Get a book from the db.")
//    void findBookByName() {
//        edition.setAllBooks(Collections.singletonList(item));
//        book.setEditions(Collections.singletonList(edition));
//        BookModel b = bookRepo.findByBookName("To Kill A Mockingbird");
//
//        assertNotNull(b);
//        assertEquals("To Kill A Mockingbird", b.getBookName());
//    }
//}