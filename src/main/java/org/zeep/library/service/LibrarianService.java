package org.zeep.library.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianCreateRequest;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianEmailChangeRequest;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianPasswordChange;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianUpdateRequest;
import org.zeep.library.domain.MemberDomain.Responses.LibrarianResponse;
import org.zeep.library.enums.Status;
import org.zeep.library.model.LibrarianModel;
import org.zeep.library.model.inheritance.Account;
import org.zeep.library.repo.LibrarianRepo;

import java.util.Optional;
import java.util.UUID;

@Service
public class LibrarianService {

    private final LibrarianRepo repo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();

    public LibrarianService(LibrarianRepo repo) {
        this.repo = repo;
    }

    public LibrarianResponse create(LibrarianCreateRequest request) {
        // creates a new librarian entity
        LibrarianModel model = LibrarianModel.builder().lastName(request.getLastName())
                .firstName(request.getFirstName())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .username(request.getUsername())
                .id(UUID.randomUUID())
                .status(Status.Active).build();

        // save and return it
        LibrarianModel m = repo.save(model);
        return LibrarianResponse.builder().body(m).responseCode(HttpStatus.CREATED.value())
                .message("Created the whole thingy").build();

    }

    public LibrarianResponse update(LibrarianUpdateRequest request) {
        Optional<Account> librarianGet = repo.findById(request.getLibrarianId());
        LibrarianModel librarian;
        if (librarianGet.isPresent()) {
            librarian = (LibrarianModel) librarianGet.get();
        } else {
            return LibrarianResponse.builder().body(null)
                    .responseCode(HttpStatus.NOT_FOUND.value()).message("Librarian not found")
                    .build();
        }
        librarian.setFirstName(request.getFirstName());
        librarian.setLastName(request.getLastName());
        return LibrarianResponse.builder().body(repo.save(librarian))
                .responseCode(HttpStatus.NOT_FOUND.value()).message("Librarian not found")
                .build();
    }

    public LibrarianResponse changePassword(LibrarianPasswordChange request) {
        Optional<Account> librarianGet = repo.findById(request.getLibrarianId());
        LibrarianModel librarian;
        if (librarianGet.isPresent()) {
            librarian = (LibrarianModel) librarianGet.get();
        } else {
            return LibrarianResponse.builder().body(null)
                    .responseCode(HttpStatus.NOT_FOUND.value()).message("Librarian not found")
                    .build();
        }
        if (!(request.getNewPassword().equalsIgnoreCase(request.getNewPassword2())
        && encoder.matches(request.getOldPassword(), librarian.getPassword()))) {
            return LibrarianResponse.builder().body(null)
                    .responseCode(HttpStatus.NOT_FOUND.value()).message("Password do not match")
                    .build();
        }
        librarian.setPassword(encoder.encode(request.getNewPassword()));
        return LibrarianResponse.builder().body(repo.save(librarian))
                .responseCode(HttpStatus.NOT_FOUND.value()).message("Librarian not found")
                .build();
    }

    public LibrarianResponse changeEmail(LibrarianEmailChangeRequest request) {
        Optional<Account> librarianGet = repo.findById(request.getLibrarianId());
        LibrarianModel librarian;
        if (librarianGet.isPresent()) {
            librarian = (LibrarianModel) librarianGet.get();
        } else {
            return LibrarianResponse.builder().body(null)
                    .responseCode(HttpStatus.NOT_FOUND.value()).message("Librarian not found")
                    .build();
        }
        librarian.setEmail(request.getEmail());
        return LibrarianResponse.builder().body(repo.save(librarian))
                .responseCode(HttpStatus.NOT_FOUND.value()).message("Librarian not found")
                .build();
    }
}