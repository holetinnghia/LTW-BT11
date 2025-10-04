// com/example/secui/web/HomeController.java
package com.example.secui.web;
import org.springframework.stereotype.Controller; import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class HomeController {
    @GetMapping("/") public String index(){ return "index"; }
    @GetMapping("/login") public String login(){ return "login"; }
    @GetMapping("/hello") public String hello(){ return "index"; }
}