package org.zeep.library.service;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zeep.library.config.LibraryCardGenerator;
import org.zeep.library.domain.MemberDomain.Requests.*;
import org.zeep.library.domain.MemberDomain.Responses.MemberResponse;
import org.zeep.library.enums.Status;
import org.zeep.library.model.Address;
import org.zeep.library.model.LibraryCardModel;
import org.zeep.library.model.MemberModel;
import org.zeep.library.model.inheritance.Account;
import org.zeep.library.repo.LibrarianRepo;
import org.zeep.library.repo.MemberRepository;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberService {

    private final MemberRepository memberRepo;
    private final PasswordEncoder encoder = new BCryptPasswordEncoder();
    private final LibraryCardGenerator cardGenerator = new LibraryCardGenerator();

    public MemberService(MemberRepository memberRepo, LibrarianRepo librarianRepo) {
        this.memberRepo = memberRepo;
    }

    public MemberResponse create(CreateMember request) {
        Address address = Address.builder().city(request.getAddress().getCity())
                .state(request.getAddress().getState()).zipcode(request.getAddress().getZipcode())
                .street(request.getAddress().getStreet()).build();
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
                .password(encoder.encode(request.getPassword()))
                .accountType(request.getAccountType())
                .status(Status.Active)
                .build();
        model.setLibraryCard(cardGenerator.create(model.getAccountType(),
                model.getFirstName(), model.getLastName()));
        MemberModel m = this.memberRepo.save(model);
        return MemberResponse.builder().body(m).responseCode(HttpStatus.CREATED.value())
                .message("Created").build();
    }

    public MemberResponse changePassword(PasswordChangeRequest request) {
        Optional<Account> model = memberRepo.findById(request.getMemberId());
        MemberModel m;
        if (model.isPresent()) {
            m = (MemberModel) model.get();
        } else {
            return MemberResponse.builder().body(null).
                    responseCode(HttpStatus.FORBIDDEN.value())
                    .message("Member not found").build();
        }

        if (!request.getNewPassword().equalsIgnoreCase(request.getNewPassword2())) {
            return MemberResponse.builder().body(null).
                    responseCode(HttpStatus.FORBIDDEN.value())
                    .message("Passwords do not match.").build();
        }

        m.setPassword(encoder.encode(request.getNewPassword()));
        return MemberResponse.builder().body(memberRepo.save(m))
                .responseCode(HttpStatus.OK.value())
                .message("Changed").build();
    }

    public MemberResponse update(MemberUpdateRequest request) {
        Optional<Account> model = memberRepo.findById(request.getMemberId());
        MemberModel m;
        if (model.isPresent()) {
            m = (MemberModel) model.get();
        } else {
            return MemberResponse.builder().body(null).
                    responseCode(HttpStatus.FORBIDDEN.value())
                    .message("Member not found").build();
        }

        m.setFirstName(request.getFirstName());
        m.setLastName(request.getLastName());
        m.getLibraryCard().setFirstName(request.getFirstName());
        m.getLibraryCard().setLastName(request.getLastName());
        return MemberResponse.builder().body(memberRepo.save(m))
                .responseCode(HttpStatus.OK.value())
                .message("Updated").build();

    }

    public MemberResponse changeUsername(ChangeUsername request) {
        Optional<Account> model = memberRepo.findById(request.getMemberId());
        MemberModel m;
        if (model.isPresent()) {
            m = (MemberModel) model.get();
        } else {
            return MemberResponse.builder().body(null).
                    responseCode(HttpStatus.FORBIDDEN.value())
                    .message("Member not found").build();
        }
        m.setUsername(request.getUsername());
        return MemberResponse.builder().body(memberRepo.save(m))
                .responseCode(HttpStatus.OK.value())
                .message("Updated").build();
    }

    public MemberResponse changeEmial(ChangeEmail request) {
        Optional<Account> model = memberRepo.findById(request.getMemberId());
        MemberModel m;
        if (model.isPresent()) {
            m = (MemberModel) model.get();
        } else {
            return MemberResponse.builder().body(null).
                    responseCode(HttpStatus.FORBIDDEN.value())
                    .message("Member not found").build();
        }
        m.setEmail(request.getEmail());
        return MemberResponse.builder().body(memberRepo.save(m))
                .responseCode(HttpStatus.OK.value())
                .message("Updated").build();
    }

    public MemberResponse changeAddress(UpdateAddress request) {
        Optional<Account> model = memberRepo.findById(request.getMemberId());
        MemberModel m;
        if (model.isPresent()) {
            m = (MemberModel) model.get();
        } else {
            return MemberResponse.builder().body(null).
                    responseCode(HttpStatus.FORBIDDEN.value())
                    .message("Member not found").build();
        }
        Address address = Address.builder().state(request.getAddress().getState())
                .city(request.getAddress().getCity())
                .zipcode(request.getAddress().getZipcode())
                .street(request.getAddress().getStreet()).build();
        m.setAddress(address);
        return MemberResponse.builder().body(memberRepo.save(m))
                .responseCode(HttpStatus.OK.value())
                .message("Updated").build();
    }
}
