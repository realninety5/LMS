package org.zeep.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeep.library.domain.MemberDomain.Requests.LibrarianCreateRequest;
import org.zeep.library.domain.MemberDomain.Responses.LibrarianResponse;
import org.zeep.library.service.LibrarianService;

import javax.validation.Valid;

@RequestMapping("l/")
@RestController
public class LibrarianController {

    @Autowired
    LibrarianService service;
    @PostMapping("sign-up")
    public ResponseEntity<LibrarianResponse> create(@Valid @RequestBody LibrarianCreateRequest request) {
        LibrarianResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public void get() {

    }

    @PutMapping
    public void update() {

    }
}
