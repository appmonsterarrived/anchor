package com.appmonster.anchor.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("/anchor")
@RestController
public class Controller {

    @GetMapping("/greeting")
    public String getGreeting(Principal principal) {
        return principal.getName();
    }

    @GetMapping("/user")
    public String getUser(Principal principal) {
        return "Hello World from User, Welcome "+ principal.getName();
    }

    @GetMapping("/admin")
    public String getAadmin(Principal principal) {
        return "Hello World from Admin, Welcome "+ principal.getName();
    }
}
