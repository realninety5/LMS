package org.zeep.library.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.zeep.library.repo.AuthorRepo;
import org.zeep.library.repo.BookRepo;
import org.zeep.library.repo.BookYearRepo;
import org.zeep.library.repo.GenreRepo;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CatalogueServiceTest {

    @InjectMocks CatalogueService service;
    @Mock BookRepo bookRepo;
    @Mock AuthorRepo authorRepo;
    @Mock GenreRepo genreRepo;
    @Mock BookYearRepo yearRepo;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getBooksByAuthor() {
    }

    @Test
    void getBookByYear() {
    }

    @Test
    void getBookByGenre() {
    }
}