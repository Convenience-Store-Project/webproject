package com.example.convenience_store.controller;

import com.example.convenience_store.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    @GetMapping("/reservation")
    public String reservationPage() {
        return "reservation";
    }

    @GetMapping("/confirm")
    public String confirmPage() {

        return "confirm";
    }
}