package org.zeep.library.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@ResponseBody
@RestController("manage/")
public class BookManagementController {
    /**
     * Possible mapping for book management endpoints
     * add/{book_id}, remove/{book_id}   all post
     * add the below line to usermanagementcontroller
     *  deactivate/{user_id}, activate/{user_id}, blacklist{user_id}/
     */

    @PostMapping("add/{book_id}")
    public void addBook() {
        //return;
    }
    @PostMapping("remove/{book_id}")
    public void removeBook() {
        //return;
    }
    @PutMapping("update/{book_id}")
    public void updateBook() {
        //return;
    }
}
