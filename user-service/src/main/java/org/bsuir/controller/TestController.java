package org.bsuir.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/messages")
public class TestController {
    @PreAuthorize("hasAnyRole('USER')")
    @GetMapping("/hi")
    public String sayHi(){
        return "HI";
    }
    @GetMapping("/login")
    public String logination(){
        return "login";
    }
}
