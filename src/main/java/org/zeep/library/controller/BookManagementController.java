package org.zeep.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeep.library.domain.BookDomain.Requests.BookAddRequest;
import org.zeep.library.domain.BookDomain.Responses.BookResponse;
import org.zeep.library.service.BookService;

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
    BookService service;

    @PostMapping("add/")
    public ResponseEntity<BookResponse> addBook(@Valid @RequestBody BookAddRequest request) {
        BookResponse response = service.addBook(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("update/{book_id}")
    public void updateBook() {
        //return;
    }
}
