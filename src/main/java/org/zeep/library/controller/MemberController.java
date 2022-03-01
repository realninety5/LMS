package org.zeep.library.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("m/")
@ResponseBody
public class MemberController {

    /**
     * This is the controller that allows users to get their profiles
     */

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
