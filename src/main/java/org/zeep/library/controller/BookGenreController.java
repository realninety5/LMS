package org.zeep.library.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zeep.library.enums.Genre;

@RestController
@RequestMapping("g/")
public class BookGenreController {

    @GetMapping("/{genre}")
    public void getBookByGenre(@PathVariable("genre") Genre genre) {

    }

}
