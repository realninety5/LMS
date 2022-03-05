package org.zeep.library.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianCreateRequest;
import org.zeep.library.domain.MemberDomain.Responses.LibrarianResponse;
import org.zeep.library.enums.Status;
import org.zeep.library.model.LibrarianModel;
import org.zeep.library.repo.LibrarianRepo;

import java.util.UUID;

@Service
public class LibrarianService {

    private final LibrarianRepo repo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public LibrarianService(LibrarianRepo repo) {
        this.repo = repo;
    }

    public LibrarianResponse create(LibrarianCreateRequest request) {
        LibrarianModel model = LibrarianModel.builder().lastName(request.getLastName())
                .firstName(request.getFirstName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .username(request.getUsername())
                .id(UUID.randomUUID())
                .status(Status.Active).build();

        LibrarianModel m = repo.save(model);
        return LibrarianResponse.builder().body(m).responseCode(HttpStatus.CREATED.value())
                .message("Created the whole thingy").build();

    }

    public LibrarianResponse update() {
        return new LibrarianResponse();
    }
}