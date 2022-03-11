package org.zeep.library.service;


import org.springframework.stereotype.Service;
import org.zeep.library.domain.BookDomain.Requests.Book.*;
import org.zeep.library.model.*;
import org.zeep.library.model.inheritance.Account;
import org.zeep.library.repo.*;

import java.util.Date;
import java.util.Optional;

@Service
public class BookService {

    final private BookItemRepo repo;
    final private MemberRepository memberRepo;
    final private BorrowedBooksRepo borrowedRepo;
    final private ReservedBooksRepo reservedRepo;

    public BookService(BookItemRepo repo, MemberRepository memberRepo, BorrowedBooksRepo borrowedRepo, ReservedBooksRepo reservedRepo) {
        this.repo = repo;
        this.memberRepo = memberRepo;
        this.borrowedRepo = borrowedRepo;
        this.reservedRepo = reservedRepo;
    }

    public boolean borrow(BookBorrowRequest request) {
        // get the bookItem and the borrower
        Optional<Account> memberModel = memberRepo.findById(request.getMemberId());
        Optional<BookItemModel> bookItem = repo.findById(request.getBookId());
        MemberModel member = (MemberModel) memberModel.get();
        BookItemModel item = bookItem.get();
        // check if the borrower has not borrowed more than three books already
        // thus, he cannot have more than 3 borrowed books
        if (!item.isAvailable() && member.getBorrowedBooks().size() > 3) {
            // you cannot borrow a new book unless you
            // return the initial 3 borrows
            // or the book is available
        }
        BooksBorrowed borrowed = new BooksBorrowed();
        borrowed.setDate(new Date());
        borrowed.setBookItem(item);
        borrowed.setBorrowedBy(member);

        //member.setBooksBorrowedCount(member.getBooksBorrowedCount()+1);
        item.setAvailable(false);
        member.getBorrowedBooks().add(item);

        item.setBorrowedBy(member);
        item.setBorrowedDate(new Date());
        borrowedRepo.save(borrowed);
        return true;
    }

    public boolean reserve(BookReservationRequest request) {
        // get the bookItem and the borrower
        Optional<Account> memberModel = memberRepo.findById(request.getMemberId());
        Optional<BookItemModel> bookItem = repo.findById(request.getBookId());
        MemberModel member = (MemberModel) memberModel.get();
        BookItemModel item = bookItem.get();
        if (item.isReserved() && member.getReservedBooks().size() > 3) {
            // the book has already been reserved
            // the member has already reserved more than 3 books
        }
        // set it up as reserved
        ReservedBooks reserved = new ReservedBooks();
        reserved.setReservedBy(member);
        reserved.setDate(new Date());
        reserved.setBookItem(item);
        // add the book to the books the member has reserved
        member.getReservedBooks().add(item);
        item.setReserved(true);
        return true;

    }

    public void returnBook() {
        // get the bookItem and the borrower
//        Optional<Account> memberModel = memberRepo.findById(request.getMemberId());
//        Optional<BookItemModel> bookItem = repo.findById(request.getBookId());
//        MemberModel member = (MemberModel) memberModel.get();
//        BookItemModel item = bookItem.get();
    }
}
