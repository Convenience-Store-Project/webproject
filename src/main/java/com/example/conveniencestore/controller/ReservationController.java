package com.example.conveniencestore.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/customers")
public class ReservationController {
    @GetMapping("")
    public String reservationProduct() {

        return "customers/reservation";
    }

    @GetMapping("/confirm")
    public String reservationConfirm() {

        return "customers/confirm";
    }
}
