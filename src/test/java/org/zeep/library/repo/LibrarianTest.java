package org.zeep.library.repo;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.enums.Status;
import org.zeep.library.model.LibrarianModel;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class LibrarianTest {

    @Mock
    LibrarianRepo repo;

    LibrarianModel model = LibrarianModel.builder().id(UUID.randomUUID()).email("og@gamil.com")
            .username("oko").firstName("James").lastName("Dave").status(Status.Active).build();

    @BeforeEach
    void setUp() {
        lenient().when(repo.findByUsername("oko")).thenReturn(model);
    }

    @Test
    @DisplayName("Returns a librarian by their username")
    void findByUsername() {
        LibrarianModel l = repo.findByUsername("oko");
        assertNotNull(l);
        assertEquals("oko", l.getUsername());
    }
}
