package com.example.convenience_store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SignupController {

    @GetMapping("/signup")
    public String showSignupPage(Model model) {
        model.addAttribute("pageTitle", "회원가입 페이지");
        return "signup"; // resources/templates/signup.html과 매핑됩니다.
    }

    // 회원가입 처리 로직은 여기에 추가해야 합니다.
}
