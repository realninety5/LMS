package org.zeep.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeep.library.domain.AuthorDomain.Response.AuthorResponse;
import org.zeep.library.service.AuthorService;

@RestController
@RequestMapping("a/")
public class AuthorController {
    @Autowired AuthorService service;

    @GetMapping("/a")
    public ResponseEntity<AuthorResponse> get() {
        AuthorResponse response = service.getAuthor();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
