package org.zeep.library.repo;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.BookItemModel;
import org.zeep.library.model.BookModel;
import org.zeep.library.model.GenreModel;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class GenreTest {

    @Mock
    GenreRepo repo;

    BookModel b = BookModel.builder().id(UUID.randomUUID()).build();
    BookModel b1 = BookModel.builder().id(UUID.randomUUID()).build();
    Set<BookModel> items = new HashSet<>();
    private final GenreModel genre = GenreModel.builder().genre(Genre.Art).id(1L)
            .build();
    @BeforeEach
    void setUp() {
        items.add(b);
        items.add(b1);
        genre.setBooks(items);
        lenient().when(repo.findByGenre(Genre.Art)).thenReturn(genre);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    @DisplayName("Get a list of books in the same genre")
    void findBookByGenre() {
        GenreModel g = repo.findByGenre(Genre.Art);
        assertNotNull(g);
        assertEquals(2, g.getBooks().size());
    }
}
