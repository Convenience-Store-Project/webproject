package com.example.convenience_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("pageTitle", "로그인 페이지");
        return "login"; // resources/templates/login.html과 매핑됩니다.
    }

    // 로그인 처리 로직은 여기에 추가해야 합니다.
}
