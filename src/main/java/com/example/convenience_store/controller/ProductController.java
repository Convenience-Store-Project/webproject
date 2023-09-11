package com.example.convenience_store.controller;

import com.example.convenience_store.model.entity.Customer;
import com.example.convenience_store.model.entity.Product;
import com.example.convenience_store.model.entity.Reservation;
import com.example.convenience_store.service.ProductService;
import com.example.convenience_store.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ReservationService reservationService;

    @GetMapping("/search")
    public String showSearchPage(HttpSession session, Model model) {
        Customer customer = (Customer) session.getAttribute("customer");
        if(customer == null){
            return "redirect:/login";
        }
        return "search";
    }

    @GetMapping("/reserve")
    public String reservationPage() {
        return "reserve";
    }

    @PostMapping("/search")
    public String searchItem(@RequestParam String name, Model model) {
        List<Product> foundProducts = productService.findProductByName(name);

        if (!foundProducts.isEmpty()) {
            model.addAttribute("foundProducts", foundProducts);
        } else {
            model.addAttribute("message", "물건을 찾을 수 없습니다.");
        }

        return "search";
    }

    @GetMapping("/reserve/{id}")
    public String reserveForm(@PathVariable Integer id, Model model, HttpSession session) {
        Product productResponse = productService.read(id);
        session.setAttribute("product", productResponse);

        model.addAttribute("productinfo", productResponse);
        return "reserve";
    }

    @PostMapping("/update")
    public String ProductUpdateForm(@ModelAttribute Product productRequest, HttpSession session) {
        Product sessionProduct = (Product) session.getAttribute("product");
        Customer sessionCustomer = (Customer) session.getAttribute("customer");

        sessionProduct.setQuantity(productRequest.getQuantity());
        Product updatedProduct = productService.update(sessionProduct);

        if(updatedProduct == null){
            return "fail";
        }

        // ReservationService에서 만들 코드
        Reservation newReservation = new Reservation();
        newReservation.setQuantity(productRequest.getQuantity());
        newReservation.setPrice(sessionProduct.getPrice());
        newReservation.setTime(Timestamp.from(Instant.now()));
        newReservation.setCustomer(sessionCustomer);
        newReservation.setProduct(updatedProduct);
        newReservation.setStore(sessionProduct.getStore());

        reservationService.save(newReservation);

        return "confirm";
    }

//    @GetMapping("/ProductId")
//    public String storeIdInSession(HttpSession session, Model model) {
//        String userId = "your_user_id"; // 저장할 사용자 ID
//        session.setAttribute("userId", userId);
//        return "redirect:/somePage"; // ID를 저장하고 다른 페이지로 리다이렉트
//    }
//
//    @GetMapping("/somePage")
//    public String showPageWithStoredId(HttpSession session, Model model) {
//        String userId = (String) session.getAttribute("userId");
//        if (userId != null) {
//            model.addAttribute("userId", userId);
//        }
//        return "somePage"; // 저장된 ID를 화면에 표시하는 페이지로 이동
//    }



}