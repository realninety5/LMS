package org.zeep.library.service;


import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.zeep.library.config.LibraryCardGenerator;
import org.zeep.library.domain.MemberDomain.Requests.CreateMember;
import org.zeep.library.domain.MemberDomain.Responses.MemberResponse;
import org.zeep.library.enums.Status;
import org.zeep.library.model.Address;
import org.zeep.library.model.LibraryCardModel;
import org.zeep.library.model.MemberModel;
import org.zeep.library.repo.LibrarianRepo;
import org.zeep.library.repo.MemberRepository;

import java.util.Date;
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
        model.setLibraryCard(cardGenerator.create(model.getAccountType()));
        MemberModel m = this.memberRepo.save(model);
        return MemberResponse.builder().body(m).responseCode(HttpStatus.CREATED.value())
                .message("Created").build();
    }
}
