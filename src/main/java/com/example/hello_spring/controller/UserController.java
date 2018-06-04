package com.example.hello_spring.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    /*@GetMapping("/logout")
    public String  logout(){
        SecurityContextHolder.clearContext();
        return "login";
    }*/

    @GetMapping("/")
    public String home(){
        return "index";
    }

    @GetMapping("/403")
    public String error403(){
        return "403";
    }
}
