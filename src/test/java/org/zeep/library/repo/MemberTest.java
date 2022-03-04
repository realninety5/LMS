package org.zeep.library.repo;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.zeep.library.enums.AccountType;
import org.zeep.library.enums.Gender;
import org.zeep.library.enums.Status;
import org.zeep.library.model.LibraryCardModel;
import org.zeep.library.model.MemberModel;
import org.zeep.library.repo.MemberRepository;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class MemberTest {
    @Mock
    MemberRepository memberRepo;

    MemberModel m = MemberModel.builder().password("how").dob(LocalDate.of(1992,8,7))
            .date_reg(new Date()).accountType(AccountType.STANDARD).id(UUID.randomUUID())
            .booksBorrowedCount(0).email("k@og.com").gender(Gender.Male).firstName("Okah")
            .lastName("jom").libraryCard(new LibraryCardModel()).status(Status.Active)
            .username("olo").build();

    @BeforeEach
    void setUp() {
        lenient().when(memberRepo.save(m)).thenReturn(m);
        lenient().when(memberRepo.findByUsername("olo")).thenReturn(m);
    }

    @AfterEach
    void tearDown() {
        memberRepo.deleteAll();
    }

    @Test
    @DisplayName("Should save a new member with the required fields and return it.")
    void save() {
        MemberModel mm = memberRepo.save(m);
        assertNotNull(mm);
    }

    @Test
    @DisplayName("Finds a member by their username.")
    void findbyUsername() {
        MemberModel mm = memberRepo.findByUsername("olo");
        assertNotNull(mm);
        assertEquals("olo", mm.getUsername());
    }

}
