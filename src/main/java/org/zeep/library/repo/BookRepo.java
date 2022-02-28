package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.BookModel;

import java.util.UUID;

public interface BookRepo extends JpaRepository<BookModel, UUID> {
    public BookModel findByBookName(String bookName);
}
