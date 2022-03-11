package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.ReservedBooks;

import java.util.UUID;

public interface ReservedBooksRepo extends JpaRepository<ReservedBooks, UUID> {
}
