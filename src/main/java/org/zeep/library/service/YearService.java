package org.zeep.library.service;

import lombok.Setter;
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

        // get the year from the db, create one if it doesn't exist
        // get its books and add this edition to it, since each edition has it own year
        // the books uuid code is also saved to the year class, this can be used to get the parent book itself.
        BookByYear year = repo.findByYear(yearValue);
        if (year == null) {
            year = repo.save(BookByYear.builder().year(yearValue)
                    .books(new HashSet<>()).build());
            year.getBooks().add(edition);
        } else {
            year.getBooks().add(edition);
        }
        return year;
    }

    public BookByYear getBookByYear(String yearValue) {
        return repo.findByYear(yearValue);
    }
}
