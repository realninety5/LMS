package org.zeep.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zeep.library.ExceptionsAndValidators.Exceptions.*;
import org.zeep.library.domain.MemberDomain.Requests.*;
import org.zeep.library.domain.MemberDomain.Responses.LibrarianResponse;
import org.zeep.library.enums.Status;
import org.zeep.library.model.LibrarianModel;
import org.zeep.library.repo.LibrarianRepo;

import java.util.UUID;

@Service
public class LibrarianService {

    @Autowired
    LibrarianRepo repo;
    @Autowired PasswordEncoder encoder;

//    public LibrarianService(LibrarianRepo repo) {
//        this.repo = repo;
//    }

    public LibrarianResponse create(LibrarianCreateRequest request) {

        // creates a new librarian entity
        LibrarianModel model = LibrarianModel.builder().lastName(request.getLastName())
                .firstName(request.getFirstName())
                .email(request.getEmail())
                // comment out when testing
                .password(encoder.encode(request.getPassword()))
                .username(request.getUsername())
                .id(UUID.randomUUID())
                .status(Status.Active).build();

        // try to save and return it
        try {
             model = repo.save(model);
        }catch (RuntimeException e) {
            throw new UsernameOrEmailAlreadyExistsException("Username already exists.");
        }
        return LibrarianResponse.builder().body(model).responseCode(HttpStatus.CREATED.value())
                .message("Created the whole thingy").build();

    }

    public LibrarianResponse update(LibrarianUpdateRequest request) {
        LibrarianModel librarian;
        try {
            librarian = repo.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }

        librarian.setFirstName(request.getFirstName());
        librarian.setLastName(request.getLastName());
        return LibrarianResponse.builder().body(repo.save(librarian))
                .responseCode(HttpStatus.OK.value()).message("Librarian created")
                .build();
    }

    public LibrarianResponse changePassword(LibrarianPasswordChange request) {
        LibrarianModel librarian;
        try {
            librarian = repo.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }

        if (!(request.getNewPassword().equalsIgnoreCase(request.getNewPassword2())
        || encoder.matches(request.getOldPassword(), librarian.getPassword()))) {
            throw new PasswordDoNotMatchException("Password do not match");
        }
        librarian.setPassword(encoder.encode(request.getNewPassword()));
        return LibrarianResponse.builder().body(repo.save(librarian))
                .responseCode(HttpStatus.OK.value()).message("Password updated")
                .build();
    }

    public LibrarianResponse changeEmail(LibrarianEmailChangeRequest request) {
        LibrarianModel librarian;
        try {
            librarian = repo.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }

        librarian.setEmail(request.getEmail());
        return LibrarianResponse.builder().body(repo.save(librarian))
                .responseCode(HttpStatus.OK.value()).message("Email updated")
                .build();
    }
}