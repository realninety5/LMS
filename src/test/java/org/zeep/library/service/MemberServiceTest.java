package org.zeep.library.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zeep.library.config.LibraryCardGenerator;
import org.zeep.library.domain.MemberDomain.Requests.*;
import org.zeep.library.domain.MemberDomain.Responses.MemberResponse;
import org.zeep.library.enums.AccountType;
import org.zeep.library.enums.Gender;
import org.zeep.library.enums.State;
import org.zeep.library.model.Address;
import org.zeep.library.model.LibraryCardModel;
import org.zeep.library.model.MemberModel;
import org.zeep.library.repo.MemberRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks MemberService service;
    @Mock
    MemberRepository repo;
    @Mock LibraryCardModel cardModel;

    @Mock
    LibraryCardGenerator generator;

    Address address = Address.builder().street("4 Akinlade Str").city("Fakolo").zipcode(123456)
            .state(State.Borno).build();
    MemberModel model = MemberModel.builder().accountType(AccountType.STANDARD).username("ninety5")
            .lastName("Godwin").firstName("Kingsley").gender(Gender.Male).dob(LocalDate.of(1992,8,7))
            .address(address).email("kk@gamil.com").build();
    LibraryCardModel card = LibraryCardModel.builder()
            .issuedDate(new Date()).accountType(model.getAccountType())
            .firstName(model.getFirstName())
            .lastName(model.getLastName())
            .build();

    AddressRequest addressRequest = AddressRequest.builder().city("").state(State.Borno).street("").build();

    CreateMember request = CreateMember.builder().accountType(AccountType.STANDARD).address(addressRequest)
            .dob(LocalDate.of(1992,8,7)).gender(Gender.Male).firstName("").lastName("")
            .email("").username("ninety5").build();

    MemberUpdateRequest updateRequest = MemberUpdateRequest.builder().username("ninety5").firstName("Kings")
            .lastName("God").build();

    ChangeUsername userRequest = ChangeUsername.builder().username("ninety5").newUsername("mosdef").build();
    ChangeEmail emailRequest = ChangeEmail.builder().username("ninety5").email("oh@hmo.com").build();
    UpdateAddress updateAddress = UpdateAddress.builder().address(addressRequest).username("ninety5").build();

    @BeforeEach
    void setUp() {
        model.setLibraryCard(card);
        lenient().when(repo.save(any(MemberModel.class))).thenReturn(model);
        lenient().when(repo.findByUsername("ninety5")).thenReturn(model);
        lenient().when(generator.create(model.getAccountType(), model.getFirstName(), model.getLastName()))
                        .thenReturn(card);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void create() {
        MemberResponse response = service.create(request);
        assertEquals(201, response.getResponseCode());
        assertNotNull(response.getBody().getUsername());
    }

    @Test
    void getMember() {
        MemberResponse response = service.getMember("ninety5");
        assertNotNull(response.getBody().getUsername());
    }

    @Test
    void update() {
        MemberResponse response = service.update(updateRequest);
        assertNotNull(response.getBody().getUsername());
    }

    @Test
    void changeUsername() {
        MemberResponse response = service.changeUsername(userRequest);
        assertNotNull(response.getBody().getUsername());
    }

    @Test
    void changeEmail() {
        MemberResponse response = service.changeEmail(emailRequest);
        assertNotNull(response.getBody().getUsername());
    }

    @Test
    void changeAddress() {
        MemberResponse response = service.changeAddress(updateAddress);
        assertNotNull(response.getBody().getUsername());
    }
}