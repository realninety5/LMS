package org.zeep.library.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.BookEditionModel;
import org.zeep.library.model.BookModel;

import java.util.UUID;

public interface BookEditionRepo extends JpaRepository<BookEditionModel, UUID> {
}
