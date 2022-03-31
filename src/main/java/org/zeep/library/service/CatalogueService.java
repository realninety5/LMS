package org.zeep.library.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.zeep.library.DTO.BookDTO;
import org.zeep.library.ExceptionsAndValidators.Exceptions.NotFoundException;
import org.zeep.library.domain.AuthorDomain.Request.AuthorGetRequest;
import org.zeep.library.domain.CatalogueDomain.Requests.*;
import org.zeep.library.domain.CatalogueDomain.Response.CatalogueResponse;
import org.zeep.library.model.*;
import org.zeep.library.repo.*;
import java.util.*;

@Service
public class CatalogueService {

    @Autowired BookRepo bookRepo;
    @Autowired AuthorRepo authorRepo;
    @Autowired GenreRepo genreRepo;
    @Autowired BookYearRepo yearRepo;

    public CatalogueResponse getBooksByAuthor(AuthorGetRequest request) {
        // this method gets all the books written by a particular author
        Author author;
        try {
                author = authorRepo.findByFirstNameAndLastName(request.getFirstName(), request.getLastName());
        } catch (RuntimeException e) {
            throw new NotFoundException("Author not found.");
        }
        Map<String, Set<BookDTO>> bookDTOs = new HashMap<>();

        for (BookModel model: author.getBooks()) {
            Set<BookDTO> books = new HashSet<>();
            for (BookEditionModel editionModel: model.getEditions()) {
                for (BookItemModel itemModel: editionModel.getAvailBooks()) {
                    books.add(new BookDTO().mapBookToYear(itemModel, editionModel, model));
                }
            }
            bookDTOs.put(model.getBookName(), books);
        }
        return CatalogueResponse.builder().responseCode(HttpStatus.OK.value())
                .message("Here are the books by "+ request.getFirstName())
                .responseCode(HttpStatus.OK.value())
                .books(bookDTOs).build();
    }

    public CatalogueResponse getBookByYear(BookByYearRequest request) {
        BookByYear year1;
        try {
            // this method gets all the editions published in a year
            year1 = yearRepo.findByYear(request.getYear());
        } catch (RuntimeException e) {
            throw new NotFoundException("No books for the given year");
        }
        //Set<BookDTO> books = new HashSet<>();
        Map<String, Set<BookDTO>> books = new HashMap<>();

        for (BookEditionModel edition : year1.getBooks()) {
            // get the available bookItems for this edition
            Set<BookItemModel> items = edition.getAvailBooks();
            // loop through the items and map this bookItem to BookDTO
            Set<BookDTO> bookDTOs = new HashSet<>();
            Optional<BookModel> bookModel = bookRepo.findById(edition.getBookId());
            if (!bookModel.isPresent()) {
                continue;
            }
            for (BookItemModel itemModel: items) {
                bookDTOs.add(new BookDTO().mapBookToYear(itemModel, edition, bookModel.get()));
            }
            // get the books (parent) of these editions
            books.put(bookModel.get().getBookName(), bookDTOs);
        }
        // add the books to a set and return it.
        return CatalogueResponse.builder().responseCode(HttpStatus.OK.value())
                .message("Here are the books of "+ request.getYear()+" by book title.")
                .responseCode(HttpStatus.OK.value())
                .books(books).build();
    }

    public CatalogueResponse getBookByGenre(BookByGenreRequest request) {
        GenreModel model;
        try {
        // find all the books in a genre and return it
        model = genreRepo.findByGenre(request.getGenre());
        } catch (RuntimeException e) {
            throw new NotFoundException("Genre not found");
        }
        // define the map
        Map<String, Set<BookDTO>> books = new HashMap<>();
        for (BookModel bookModel: model.getBooks()) {
            Set<BookDTO> bookDTOs = new HashSet<>();
            for (BookEditionModel editionModel: bookModel.getEditions()) {
                for (BookItemModel itemModel: editionModel.getAvailBooks()) {
                    bookDTOs.add(new BookDTO().mapBookToYear(itemModel, editionModel, bookModel));
                }
            }
            books.put(bookModel.getBookName(), bookDTOs);
        }

        return CatalogueResponse.builder().books(books)
                .responseCode(HttpStatus.OK.value())
                .message("Here are the books in "+request.getGenre()+".").build();
    }
}
