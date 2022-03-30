package org.zeep.library.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianCreateRequest;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianEmailChangeRequest;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianUpdateRequest;
import org.zeep.library.domain.MemberDomain.Responses.LibrarianResponse;
import org.zeep.library.enums.Status;
import org.zeep.library.model.LibrarianModel;
import org.zeep.library.repo.LibrarianRepo;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class LibrarianServiceTest {

    @InjectMocks LibrarianService service;
    @Mock
    LibrarianRepo repo;

    LibrarianModel model = LibrarianModel.builder().id(UUID.randomUUID()).email("ok@gmail.com").status(Status.Active)
            .firstName("First").lastName("Jesus").username("dave").build();

    LibrarianCreateRequest request = LibrarianCreateRequest.builder().lastName("First").lastName("Jesus")
            .email("ok@gmail.com").username("dave").build();

    LibrarianUpdateRequest updateRequest = LibrarianUpdateRequest.builder().lastName("Jeus").firstName("LOst")
            .username("yesboss").build();

    LibrarianEmailChangeRequest changeRequest = LibrarianEmailChangeRequest.builder().username("yesboss")
            .email("ok@gmail.com").build();

    @BeforeEach
    void setUp() {
        lenient().when(repo.save(any(LibrarianModel.class))).thenReturn(model);
        lenient().when(repo.findByUsername("yesboss")).thenReturn(model);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        LibrarianResponse resp = service.create(request);
        assertEquals(201, resp.getResponseCode());
        assertNotNull(resp.getBody().getUsername());
    }

    @Test
    void update() {
        LibrarianResponse resp = service.update(updateRequest);
        assertEquals(200, resp.getResponseCode());
        assertNotNull(resp.getBody().getUsername());
    }

    @Test
    void changeEmail() {
        LibrarianResponse resp = service.changeEmail(changeRequest);
        assertEquals(200, resp.getResponseCode());
        assertNotNull(resp.getBody().getUsername());
    }
}