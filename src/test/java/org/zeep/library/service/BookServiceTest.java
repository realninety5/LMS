package org.zeep.library.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.IndicativeSentencesGeneration;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.zeep.library.domain.BookDomain.Requests.Book.BookBorrowRequest;
import org.zeep.library.domain.BookDomain.Requests.Book.BookReservationRequest;
import org.zeep.library.domain.BookDomain.Requests.Book.BookReturnRequest;
import org.zeep.library.domain.BookDomain.Responses.BookResponse;
import org.zeep.library.model.*;
import org.zeep.library.model.inheritance.Account;
import org.zeep.library.repo.BookItemRepo;
import org.zeep.library.repo.BorrowedBooksRepo;
import org.zeep.library.repo.MemberRepository;
import org.zeep.library.repo.ReservedBooksRepo;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @InjectMocks BookService service;
    @Mock BookItemRepo repo;
    @Mock MemberRepository memberRepo;
    @Mock BorrowedBooksRepo borrowedRepo;
    @Mock ReservedBooksRepo reservedRepo;

    private final Set<BookItemModel> borrowedBooks = new HashSet<>();
    private final Set<BookItemModel> reservedBooks = new HashSet<>();
    private final BookItemModel model = BookItemModel.builder().id(UUID.randomUUID()).available(false).reserved(false).build();

//    private final BookItemModel model = BookItemModel.builder().id(UUID.randomUUID()).available(true).reserved(true).build();
    private final MemberModel memberModel = MemberModel.builder().id(UUID.randomUUID()).username("ninety5").build();
    private final BooksBorrowed borrowed = BooksBorrowed.builder().id(UUID.randomUUID())
            .bookItem(model).borrowedBy((MemberModel) memberModel).build();
    private final BookBorrowRequest borrowRequest = BookBorrowRequest.builder().bookId(model.getId())
            .username(memberModel.getUsername()).build();
    private final BookReservationRequest reserveRequest = BookReservationRequest.builder().bookId(model.getId())
            .username(memberModel.getUsername()).build();
    private final BookReturnRequest returnRequest = BookReturnRequest.builder().bookId(model.getId())
            .username(memberModel.getUsername()).build();

    @BeforeEach
    void setUp() {
        borrowedBooks.add(new BookItemModel());
        borrowedBooks.add(new BookItemModel());
        memberModel.setBorrowedBooks(borrowedBooks);
        memberModel.setReservedBooks(reservedBooks);
        model.setBorrowedBy(memberModel);
        lenient().when(repo.findById(model.getId())).thenReturn((Optional<BookItemModel>) Optional.of(model));
        lenient().when(memberRepo.findByUsername(memberModel.getUsername())).thenReturn(memberModel);
        lenient().when(borrowedRepo.save(borrowed)).thenReturn(borrowed);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void borrow() {
        boolean result = service.borrow(borrowRequest);
        assertTrue(result);
    }

    @Test
    void reserve() {
        boolean result = service.reserve(reserveRequest);
        assertTrue(result);
    }

    @Test
    void returnBook() {
        boolean result = service.returnBook(returnRequest);
        assertTrue(result);
    }
}