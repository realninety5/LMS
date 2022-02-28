package org.zeep.library.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController("books/")
public class BookController {
    /**
     * Mapping of possible endpoints
     * borrow/{book_id}, reserve/{book_id}, return/{book_id} : all post
     */

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
