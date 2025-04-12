package com.example.security.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class MyController {

    @GetMapping("/test")
    public String testAPI() {
        return "Application is UP!";
    }

    @GetMapping("/secure1")
    public String secureAPI() {
        // can only be accessed by ADMIN
        return "You are accessing a Secure API 1";
    }

    @GetMapping("/secure2")
    public String secureAPI2() {
        // can only be accessed by USER
        return "You are accessing a Secure API 2";
    }

    @GetMapping("/secure3")
    public String secureAPI3() {
        // can be accessed by both USER & ADMIN
        return "You are accessing a Secure API 3";
    }
}
