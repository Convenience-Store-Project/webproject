package com.example.convenience_store.controller;

import com.example.convenience_store.model.entity.Customer;
import com.example.convenience_store.model.entity.Product;
import com.example.convenience_store.model.entity.Reservation;
import com.example.convenience_store.repository.ProductRepository;
import com.example.convenience_store.service.CustomerService;
import com.example.convenience_store.service.ProductService;
import com.example.convenience_store.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private ReservationService reservationService;


    @GetMapping("/search")
    public String showSearchPage(HttpSession session, Model model0) {
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
        session.setAttribute("productId", id);

        Product productRequest = Product.builder()
                .store(productResponse.getStore())
                .name(productResponse.getName())
                .quantity(productResponse.getQuantity())
                .price(productResponse.getPrice())
                .build();

        session.setAttribute("product", productRequest);
        model.addAttribute("productinfo", productRequest);
        return "reserve";
    }

    @PostMapping("/update")
    public String ProductUpdateForm(@ModelAttribute Product productRequest, HttpSession session) {

        Integer id = (Integer) session.getAttribute("productId");
        productRequest.setProductId(id);
        Product product = productService.update(productRequest);

        if (product == null) {
            return "fail";
        }

//        Product confirmProduct = (Product) session.getAttribute("product");
        Customer confirmCustomer = (Customer) session.getAttribute("customer");

        // Reservation 엔티티를 빌더를 사용하여 생성
        Reservation reservation = Reservation.builder()
                .customer(confirmCustomer)
                .product(product)
                .store(product.getStore())
                .quantity(product.getQuantity())
                .price(product.getPrice())
                .time(Timestamp.from(Instant.now())) // 현재 시간을 Timestamp로 변환하여 설정
                .build();

        // 생성된 Reservation 엔티티를 데이터베이스에 저장
        reservationService.create(reservation);

        // 다른 페이지로 리다이렉트 또는 결과를 반환할 수 있음
        return "redirect:/search"; // 검색 페이지로 리다이렉트

    }
}










