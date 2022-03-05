package org.zeep.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.zeep.library.model.BookModel;
import org.zeep.library.service.BookService;

@ResponseBody
@RestController("books/")
public class BookController {
    /**
     * Mapping of possible endpoints
     * borrow/{book_id}, reserve/{book_id}, return/{book_id} : all post
     */

    @Autowired
    BookService service;

    @GetMapping("borrow/{book_id}")
    public void borrowBook() {

    }

    @GetMapping("return/{book_id}")
    public void returnBook() {

    }

    @GetMapping("reserve/{book_id}")
    public void reserveBook() {

    }

    @PostMapping("books/")
    public void books() {

    }
}
