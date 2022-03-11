package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.BooksBorrowed;

import java.util.UUID;

public interface BorrowedBooksRepo extends JpaRepository<BooksBorrowed, UUID> {
}
