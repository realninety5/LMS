package org.zeep.library.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zeep.library.ExceptionsAndValidators.Exceptions.*;
import org.zeep.library.config.LibraryCardGenerator;
import org.zeep.library.domain.MemberDomain.Requests.*;
import org.zeep.library.domain.MemberDomain.Responses.MemberResponse;
import org.zeep.library.enums.Status;
import org.zeep.library.model.*;
import org.zeep.library.repo.*;

import java.util.*;
@Service
public class MemberService {

    @Autowired MemberRepository memberRepo;
    @Autowired PasswordEncoder encoder = new BCryptPasswordEncoder();
    LibraryCardGenerator cardGenerator = new LibraryCardGenerator();

//    public MemberService(MemberRepository memberRepo, LibrarianRepo librarianRepo) {
//        this.memberRepo = memberRepo;
//    }

    public MemberResponse create(CreateMember request) throws UsernameOrEmailAlreadyExistsException {
        // create an address object from the user's response
        Address address = Address.builder().city(request.getAddress().getCity())
                .state(request.getAddress().getState()).zipcode(request.getAddress().getZipcode())
                .street(request.getAddress().getStreet()).build();
        // create the member model, and add the address to it
        MemberModel model = MemberModel.builder().accountType(request.getAccountType())
                .date_reg(new Date())
                .id(UUID.randomUUID())
                .dob(request.getDob())
                .address(address)
                .libraryCard(new LibraryCardModel())
                .gender(request.getGender())
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                // comment out when testing
                .password(encoder.encode(request.getPassword()))
                .accountType(request.getAccountType())
                .status(Status.Active)
                .build();
        // call the library card generator to generate a library card for the new member
        model.setLibraryCard(cardGenerator.create(model.getAccountType(),
                model.getFirstName(), model.getLastName()));

        // save and return
        try {
             model = memberRepo.save(model);
        } catch (RuntimeException e) {
            throw new UsernameOrEmailAlreadyExistsException("Username or email already exists.");
        }
        return MemberResponse.builder().body(model).responseCode(HttpStatus.CREATED.value())
                .message("Created").build();
    }

    public MemberResponse getMember(String username) {
        MemberModel model;
        try {
            model = memberRepo.findByUsername(username);
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }
        return MemberResponse.builder().body(model).responseCode(HttpStatus.OK.value())
                .message("Here it is.").build();
    }

    public MemberResponse changePassword(PasswordChangeRequest request) {

        MemberModel model;
        try {
            model = memberRepo.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }

        // check if the first password matches the second password
        if (!(request.getNewPassword().equalsIgnoreCase(request.getNewPassword2())
        && encoder.matches(request.getOldPassword(), model.getPassword()))) {
            throw new PasswordDoNotMatchException("Password do not match.");
        }

        // set and return
        model.setPassword(encoder.encode(request.getNewPassword()));
        return MemberResponse.builder().body(memberRepo.save(model))
                .responseCode(HttpStatus.OK.value())
                .message("Changed").build();
    }

    public MemberResponse update(MemberUpdateRequest request) {
        MemberModel model;
        try {
             model = memberRepo.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }

        // update the members details with the provided details
        model.setFirstName(request.getFirstName());
        model.setLastName(request.getLastName());
        model.getLibraryCard().setFirstName(request.getFirstName());
        model.getLibraryCard().setLastName(request.getLastName());
        return MemberResponse.builder().body(memberRepo.save(model))
                .responseCode(HttpStatus.OK.value())
                .message("Updated").build();

    }

    public MemberResponse changeUsername(ChangeUsername request) {

        MemberModel model;
        try {
            model = memberRepo.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }

        // set, save and return the updated member
        model.setUsername(request.getUsername());
        return MemberResponse.builder().body(memberRepo.save(model))
                .responseCode(HttpStatus.OK.value())
                .message("Updated").build();
    }

    public MemberResponse changeEmail(ChangeEmail request) {

        MemberModel model;
        try {
            model = memberRepo.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }
        // set, save and return the updated member
        model.setEmail(request.getEmail());
        return MemberResponse.builder().body(memberRepo.save(model))
                .responseCode(HttpStatus.OK.value())
                .message("Updated").build();
    }

    public MemberResponse changeAddress(UpdateAddress request) {

        MemberModel model;
        try {
            model = memberRepo.findByUsername(request.getUsername());
        } catch (RuntimeException e) {
            throw new NotFoundException("Member does not exist");
        }
        // set, save and return the updated member
        Address address = Address.builder().state(request.getAddress().getState())
                .city(request.getAddress().getCity())
                .zipcode(request.getAddress().getZipcode())
                .street(request.getAddress().getStreet()).build();
        model.setAddress(address);
        return MemberResponse.builder().body(memberRepo.save(model))
                .responseCode(HttpStatus.OK.value())
                .message("Updated").build();
    }
}
