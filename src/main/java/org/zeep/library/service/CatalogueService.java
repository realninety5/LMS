package org.zeep.library.service;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeep.library.domain.AuthorDomain.Request.AuthorRequest;
import org.zeep.library.domain.CatalogueDomain.Requests.*;
import org.zeep.library.domain.CatalogueDomain.Response.CatalogueResponse;
import org.zeep.library.model.*;
import org.zeep.library.repo.*;
import java.util.*;

@Service
public class CatalogueService {

    final private BookRepo bookRepo;
    final private AuthorRepo authorRepo;
    final private GenreRepo genreRepo;
    final private BookYearRepo yearRepo;

    public CatalogueService(BookRepo bookRepo, AuthorRepo authorRepo, GenreRepo genreRepo, BookYearRepo yearRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
        this.genreRepo = genreRepo;
        this.yearRepo = yearRepo;
    }

    public CatalogueResponse getBooksByAuthor(AuthorRequest request) {
        Author author1 = authorRepo.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        return CatalogueResponse.builder().responseCode(HttpStatus.OK.value())
                .message("Here are the books by "+ request.getFirstName())
                .responseCode(HttpStatus.OK.value())
                .books(author1.getBooks()).build();
    }

    public CatalogueResponse getBookByYear(BookByYearRequest request) {
        BookByYear year1 = yearRepo.findByYear(request.getYear());
        Set<BookModel> books = new HashSet<>();
        for (BookEditionModel edition : year1.getBooks()) {
            Optional<BookModel> b = bookRepo.findById(edition.getBookId());
            books.add(b.orElse(new BookModel()));
        }
        return CatalogueResponse.builder().responseCode(HttpStatus.OK.value())
                .message("Here are the books by "+ request.getYear())
                .responseCode(HttpStatus.OK.value())
                .books(books).build();
    }

    public CatalogueResponse getBookByGenre(BookByGenreRequest request) {
        GenreModel model = genreRepo.findByGenre(request.getGenre());
        return CatalogueResponse.builder().books(model.getBooks())
                .responseCode(HttpStatus.OK.value())
                .message("Here are the books in "+request.getGenre()).build();
    }
}
