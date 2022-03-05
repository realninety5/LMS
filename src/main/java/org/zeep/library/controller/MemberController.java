package org.zeep.library.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zeep.library.domain.MemberDomain.Requests.CreateMember;
import org.zeep.library.domain.MemberDomain.Responses.MemberResponse;
import org.zeep.library.service.MemberService;

import javax.validation.Valid;

@RestController
@RequestMapping("m/")
public class MemberController {

    /**
     * This is the controller that allows users to get their profiles
     */

    @Autowired
    MemberService service;

    @PostMapping("sign-up/")
    public ResponseEntity<MemberResponse> create(@Valid @RequestBody CreateMember request) {
        MemberResponse response = service.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{user_id}/")
    public void getUser() {
        return;
    }

    @GetMapping("notification/")
    public void getNotification() {
        return;
    }

    @GetMapping("reservation/")
    public void getReservation() {
        return;
    }


}
