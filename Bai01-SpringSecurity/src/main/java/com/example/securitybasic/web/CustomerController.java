package com.example.securitybasic.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello, public";
    }

    @GetMapping("/customers")
    public String customers() {
        return "Customers: secured endpoint";
    }
}