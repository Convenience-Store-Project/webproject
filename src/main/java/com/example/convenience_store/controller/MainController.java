package com.example.convenience_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String mainPage() {
        return "index"; // resources/templates/index.html과 매핑됩니다.
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login"; // resources/templates/login.html과 매핑됩니다.
    }
}