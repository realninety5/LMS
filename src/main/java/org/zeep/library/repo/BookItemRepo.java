package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.BookItemModel;
import org.zeep.library.model.BookModel;

import java.util.List;
import java.util.UUID;

public interface BookItemRepo extends JpaRepository<BookItemModel, UUID> {
////    public BookItemModel findByBookName(String bookName);
//    public List<BookItemModel> findAllByAvailable(Boolean available);
}
