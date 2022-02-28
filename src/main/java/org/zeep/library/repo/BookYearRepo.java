package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.model.BookByYear;

public interface BookYearRepo extends JpaRepository<BookByYear, Long> {
    public BookByYear findByYear(String year);
}
