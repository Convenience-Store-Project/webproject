package com.example.convenience_store.controller;

import com.example.convenience_store.model.entity.Customer;
import com.example.convenience_store.model.entity.Product;
import com.example.convenience_store.repository.ProductRepository;
import com.example.convenience_store.service.CustomerService;
import com.example.convenience_store.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

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


        model.addAttribute("productinfo", productRequest);
        return "reserve";
    }

    @PostMapping("/update")
    public String ProductUpdateForm(@ModelAttribute Product productRequest, HttpSession session) {
        Integer id = (Integer) session.getAttribute("productId");
        productRequest.setProductId(id);
        Product product = productService.update(productRequest);

        if(product == null){
            return "fail";
        }
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
