package com.example.convenience_store.controller;

import com.example.convenience_store.model.entity.Product;
import com.example.convenience_store.repository.ReservationRepository;
import com.example.convenience_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Controller
public class ReservationController {
    @Autowired
    private ReservationRepository reservationRepository;
    private ProductService productService;
    @GetMapping("/reservation")
    public String reservationPage() {
        return "reservation";
    }

    @GetMapping("/confirm")
    public String confirmPage() {

        return "confirm";
    }
    @GetMapping("/reservation/{id}")
    public String reservationForm(@PathVariable Integer id, Model model) {
        Product productResponse = productService.read(id);

        Product productRequest = Product.builder()
                        .product

        model.addAttribute("studentinfo", studentRequest);
        return "students/detail_list";
        return "reservation";
    }
}