package org.zeep.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeep.library.domain.BookDomain.Requests.Manage.BookAddRequest;
import org.zeep.library.domain.BookDomain.Requests.Manage.BookGetRequest;
import org.zeep.library.domain.BookDomain.Requests.Manage.EditionAddRequest;
import org.zeep.library.domain.BookDomain.Responses.BookResponse;
import org.zeep.library.service.BookManagementService;

import javax.validation.Valid;

@RestController
@RequestMapping("manage/")
public class BookManagementController {
    /**
     * Possible mapping for book management endpoints
     * add/{book_id}, remove/{book_id}   all post
     * add the below line to usermanagementcontroller
     *  deactivate/{user_id}, activate/{user_id}, blacklist{user_id}/
     */

    @Autowired
    BookManagementService service;

    @PostMapping("add/")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookAddRequest request) {
        BookResponse response = service.addBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("update/{book_id}")
    public void updateBook() {
        //return;
    }

    @PutMapping("add-edition")
    public ResponseEntity<BookResponse> addEdition(
            @Valid @RequestBody EditionAddRequest request) {
        BookResponse response = service.addEdition(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("")
    public ResponseEntity<BookResponse> getBook(@Valid @RequestBody BookGetRequest request) {
        BookResponse response = service.getBook(request.getId());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
