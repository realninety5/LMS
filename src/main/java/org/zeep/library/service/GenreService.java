package org.zeep.library.service;

import org.springframework.stereotype.Service;
import org.zeep.library.domain.CatalogueDomain.Requests.BookByGenreRequest;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.BookModel;
import org.zeep.library.model.GenreModel;
import org.zeep.library.repo.GenreRepo;

import java.util.HashSet;

@Service
public class GenreService {

    final private GenreRepo repo;

    public GenreService(GenreRepo repo) {
        this.repo = repo;
    }


    public GenreModel create(Genre genreValue, BookModel book) {

        // get the genre from the db, create one if it doesn't exist
        // get its books and add the bookModel to it
        GenreModel model = repo.findByGenre(genreValue);
        if (model == null) {
            model = repo.save(GenreModel.builder().genre(genreValue)
                    .books(new HashSet<>()).build());
            model.getBooks().add(book);
        } else {
            model.getBooks().add(book);
        }
        return model;
    }

    public GenreModel getBooksByGenre(Genre genre) {
        return repo.findByGenre(genre);
    }
}
