package org.zeep.library.repo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.model.Author;
import org.zeep.library.model.BookModel;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class AuthorRepoTest {

    @Mock
    AuthorRepo repo;

    BookModel b = BookModel.builder().id(UUID.randomUUID()).build();
    BookModel b1 = BookModel.builder().id(UUID.randomUUID()).build();
    Set<BookModel> books = new HashSet<>();
    Author author = Author.builder().firstName("Tolkien").lastName("John").initial("R").id(UUID.randomUUID())
            .build();

    @BeforeEach
    void setUp() {
        books.add(b);
        books.add(b1);
        author.setBooks(books);
        lenient().when(repo.findByFirstNameAndLastName("Tolkien", "John")).thenReturn(author);
        lenient().when(repo.findByFirstName("Tolkien")).thenReturn(Collections.singletonList(author));
    }

    @Test
    void findByFirstNameAndLastName() {
        Author a = repo.findByFirstNameAndLastName("Tolkien", "John");
        assertNotNull(a);
        assertEquals(2, a.getBooks().size());
        assertEquals("Tolkien", a.getFirstName());
    }

    @Test
    void findByFirstName() {
        List<Author> a = repo.findByFirstName("Tolkien");
        assertNotNull(a);
        assertEquals(2, a.get(0).getBooks().size());
        assertEquals("Tolkien", a.get(0).getFirstName());
    }
}