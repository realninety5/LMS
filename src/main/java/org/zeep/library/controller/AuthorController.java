package org.zeep.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeep.library.domain.AuthorDomain.Request.AuthorGetRequest;
import org.zeep.library.domain.AuthorDomain.Response.AuthorResponse;
import org.zeep.library.service.AuthorService;

import javax.validation.Valid;

@RestController
@RequestMapping("a/")
public class AuthorController {
    @Autowired AuthorService service;

    @GetMapping("/a")
    public ResponseEntity<AuthorResponse> get(@Valid @RequestBody AuthorGetRequest request) {
        AuthorResponse response = service.getAuthor(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
