package com.example.demospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String home() { return "index"; }
    
    @GetMapping("/user")
    public String userMenu() { return "user-menu"; }
    
    @GetMapping("/koleksi")
    public String koleksiMenu() { return "koleksi-menu"; }
}