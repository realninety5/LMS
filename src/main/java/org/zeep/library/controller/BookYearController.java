package org.zeep.library.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("y/")
public class BookYearController {

    @GetMapping("{year}")
    public void getBookByYear(@PathVariable("year") String year) {

    }
}
