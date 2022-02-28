package org.zeep.library.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.zeep.library.enums.Genre;
import org.zeep.library.model.GenreModel;

public interface GenreRepo extends JpaRepository<GenreModel, Long> {
    public GenreModel findByGenre(Genre genre);
}
