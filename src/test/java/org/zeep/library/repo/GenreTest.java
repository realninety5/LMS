//package org.zeep.library.repo;
//
//
//import org.junit.jupiter.api.AfterEach;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.zeep.library.enums.Genre;
//import org.zeep.library.model.BookItemModel;
//import org.zeep.library.model.GenreModel;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.lenient;
//
//@ExtendWith(MockitoExtension.class)
//@SpringBootTest
//public class GenreTest {
//
//    @Mock
//    GenreRepo repo;
//
//    BookItemModel b = BookItemModel.builder().id(UUID.randomUUID()).build();
//    BookItemModel b1 = BookItemModel.builder().id(UUID.randomUUID()).build();
//
//    private final GenreModel genre = GenreModel.builder().genre(Genre.Art).id(1L).
//            books(Arrays.asList(b, b1)).build();
//    @BeforeEach
//    void setUp() {
//        lenient().when(repo.findByGenre(Genre.Art)).thenReturn(genre);
//    }
//
//    @AfterEach
//    void tearDown() {
//
//    }
//
//    @Test
//    @DisplayName("Get a list of books in the same genre")
//    void findBookByGenre() {
//        GenreModel g = repo.findByGenre(Genre.Art);
//        assertNotNull(g);
//        assertEquals(2, g.getBooks().size());
//    }
//}
