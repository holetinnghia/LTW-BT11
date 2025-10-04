// web/HomeController.java
package com.example.securitydb.web;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
    @GetMapping("/") public String root() { return "forward:/hello"; }
    @ResponseBody
    @GetMapping("/hello") public String hello(){ return "Hello (public)"; }
    @ResponseBody @GetMapping("/profile") public String profile(){ return "Profile (secured)"; }
}