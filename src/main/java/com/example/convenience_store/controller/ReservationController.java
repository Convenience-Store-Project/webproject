//package com.example.convenience_store.controller;
//import com.example.convenience_store.model.entity.Customer;
//import com.example.convenience_store.model.entity.Product;
//import com.example.convenience_store.model.entity.Reservation;
//import com.example.convenience_store.service.ReservationService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//
//import javax.servlet.http.HttpSession;
//
//
//@Controller
//public class ReservationController {
//    @Autowired
//    private ReservationService reservationService;
//
//    @PostMapping("/confirm")
//    public String ReservationProductForm(@ModelAttribute Reservation reservationRequest, HttpSession session) {
//
//        Integer id = (Integer) session.getAttribute("productId");
//        Customer customer = (Customer) session.getAttribute("customer");
//        System.out.println(customer.getCustomer_id());
//
//        Reservation reservation = reservationService.create(reservationRequest);
//        if(reservation == null){
//            System.out.println("안됩니당");
//        }
//
//        return "confirm";
//    }
//}
