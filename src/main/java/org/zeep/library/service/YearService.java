package org.zeep.library.service;

import org.springframework.stereotype.Service;
import org.zeep.library.model.BookByYear;
import org.zeep.library.model.BookEditionModel;
import org.zeep.library.repo.BookYearRepo;

import java.util.HashSet;

@Service
public class YearService {

    private final BookYearRepo repo;

    public YearService(BookYearRepo repo) {
        this.repo = repo;
    }

    public BookByYear create(String yearValue, BookEditionModel edition) {
        BookByYear year1 = repo.findByYear(yearValue);
        if (year1 == null) {
            BookByYear year = BookByYear.builder().year(yearValue)
                    .books(new HashSet<>()).build();
            year.getBooks().add(edition);
        } else {
            year1.getBooks().add(edition);
        }
        return year1;
    }

    public BookByYear getBookByYear(String yearValue) {
        return repo.findByYear(yearValue);
    }
}
