package org.bsuir.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class LoginationController {
    @GetMapping("/login")
    public String logination(){
        return "login";
    }
}
