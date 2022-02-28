package org.zeep.library.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookItemModel;

import java.util.Arrays;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AuthorRepoTest {

    @Mock
    AuthorRepo repo;

    BookItemModel b = BookItemModel.builder().id(UUID.randomUUID()).build();
    BookItemModel b1 = BookItemModel.builder().id(UUID.randomUUID()).build();

    Author author = Author.builder().authorName("J. R. R. Tolkien").id(UUID.randomUUID())
            .books(Arrays.asList(b, b1)).build();

    @BeforeEach
    void setUp() {
        lenient().when(repo.findByAuthorName("J. R. R. Tolkien")).thenReturn(author);
    }

    @Test
    void findByAuthorName() {
        Author a = repo.findByAuthorName("J. R. R. Tolkien");
        assertNotNull(a);
        assertEquals(2, a.getBooks().size());
        assertEquals("J. R. R. Tolkien", a.getAuthorName());
    }
}