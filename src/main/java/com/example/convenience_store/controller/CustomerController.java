package com.example.convenience_store.controller;

import com.example.convenience_store.model.entity.Customer;
import com.example.convenience_store.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {
//    @GetMapping("/login")
//    public String showLoginPage(Model model) {
//        model.addAttribute("pageTitle", "로그인 페이지");
//        return "login"; // resources/templates/login.html과 매핑됩니다.
//    }
//
//    @GetMapping("/signup")
//    public String showSignupPage(Model model) {
//        model.addAttribute("pageTitle", "회원가입 페이지");
//        return "signup"; // resources/templates/signup.html과 매핑됩니다.
//    }
    @Autowired
    private CustomerService customerService;

    @GetMapping("")
    public String listAllCustomers(Model model) {
        List<Customer> Customers = customerService.getAllCustomers();
        model.addAttribute("customerinfo", Customers);
        return "customers/mainpage";
    }

    @GetMapping("/signup")
    public String createCustomerForm(Model model) {
        model.addAttribute("customerinfo", new Customer());
        return "customers/signup";
    }

    @PostMapping("/signup")
    public String handleCreateCustomerForm(@ModelAttribute Customer CustomerRequest) {
        Customer Customer = customerService.create(CustomerRequest);

        if(Customer == null){
            return "customers/createFail";
        }


        return "redirect:/mainpage";
    }

    @GetMapping("/createFail")
    public String createFail() {
        return "customers/createFail";
    }

    @PostMapping("/update/{id}")
    public String handleUpdateCustomerForm(@PathVariable Integer id, @ModelAttribute Customer CustomerRequest) {
        // id 매개변수 추가
        // 멤버필드 대신 사용 this.id
        CustomerRequest.setCustomerId(id);
        Customer Customer = customerService.update(CustomerRequest);

        if(Customer == null){
            return "customers/createFail";
        }
        return "redirect:/mainpage";
    }


    @GetMapping("/detail_list/{id}")
    public String updateCustomerForm(@PathVariable Integer id, Model model) {
//        this.id = id;
        Customer CustomerResponse = customerService.read(id);

        Customer CustomerRequest = Customer.builder()
                .customerId(CustomerResponse.getCustomerId())
                .name(CustomerResponse.getName())
                .id(CustomerResponse.getId())
                .password(CustomerResponse.getPassword())
                .build();

        model.addAttribute("customerinfo", CustomerRequest);
        return "customers/signup";
    }
    @GetMapping ("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        System.out.println("삭제완료");
        return "redirect:/mainpage";
    }


}
