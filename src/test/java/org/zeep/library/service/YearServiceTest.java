package org.zeep.library.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.model.BookByYear;
import org.zeep.library.model.BookEditionModel;
import org.zeep.library.model.BookModel;
import org.zeep.library.repo.BookYearRepo;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.refEq;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class YearServiceTest {

    @InjectMocks YearService service;
    @Mock
    BookYearRepo repo;
    private final Set<BookEditionModel> editions = new HashSet<>();
    private final BookByYear year = BookByYear.builder().year("2006").id(1L).build();
    BookEditionModel model = BookEditionModel.builder().bookId(UUID.randomUUID())
            .year(year).isbn("1234567890").build();

    @BeforeEach
    void setUp() {
        service = new YearService(repo);
        editions.add(model);
        //editions.add(new BookEditionModel());
        year.setBooks(editions);
        lenient().when(repo.save(year)).thenReturn(year);
        lenient().when(repo.findByYear("2006")).thenReturn(year);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        BookByYear byYear = service.create("2006", model);
        assertEquals("2006", byYear.getYear());
    }

    @Test
    void getBookByYear() {
        BookByYear byYear = service.getBookByYear("2006");
        System.out.println(byYear.getBooks().size());
        for (BookEditionModel edition: byYear.getBooks()){
            assertEquals("1234567890", edition.getIsbn());
        }
    }
}