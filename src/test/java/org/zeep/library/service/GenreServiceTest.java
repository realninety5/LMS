package org.zeep.library.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.BookModel;
import org.zeep.library.model.GenreModel;
import org.zeep.library.repo.GenreRepo;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;


@SpringBootTest
@ExtendWith(MockitoExtension.class)
class GenreServiceTest {

    @InjectMocks GenreService service;
    @Mock
    GenreRepo repo;

    private final GenreModel model = GenreModel.builder().id(1L).genre(Genre.Art).build();
    private final BookModel book = BookModel.builder().id(UUID.randomUUID()).genre(model)
            .desc("This is it, the best.").build();

    @BeforeEach
    void setUp() {
        service = new GenreService(repo);
        Set<BookModel> books = new HashSet<>();
        books.add(book);
        model.setBooks(books);
        lenient().when(repo.save(model)).thenReturn(model);
        lenient().when(repo.findByGenre(Genre.Art)).thenReturn(model);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        GenreModel resp = service.create(Genre.Art, book);
        assertEquals(Genre.Art, resp.getGenre());
        assertEquals(1, resp.getBooks().size());
    }

    @Test
    void getBooksByGenre() {
        GenreModel resp = service.getBooksByGenre(Genre.Art);
        assertEquals(Genre.Art, resp.getGenre());
        assertEquals(1, resp.getBooks().size());
    }
}