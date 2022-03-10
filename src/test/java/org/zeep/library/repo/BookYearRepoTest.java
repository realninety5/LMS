package org.zeep.library.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.model.BookByYear;
import org.zeep.library.model.BookEditionModel;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class BookYearRepoTest {

    BookEditionModel b = BookEditionModel.builder().id(UUID.randomUUID()).build();
    BookEditionModel b1 = BookEditionModel.builder().id(UUID.randomUUID()).build();

    @Mock
    BookYearRepo repo;
    private final BookByYear year = BookByYear.builder().id(1L).year("1992").build();

    @BeforeEach
    void setUp() {
        Set<BookEditionModel> editions = new HashSet<>();
        editions.add(b);
        editions.add(b1);
        year.setBooks(editions);
        lenient().when(repo.findByYear("1992")).thenReturn(year);
    }

    @Test
    void findByYear() {
        BookByYear y = repo.findByYear("1992");
        assertNotNull(y);
        assertEquals(2, y.getBooks().size());
    }
}