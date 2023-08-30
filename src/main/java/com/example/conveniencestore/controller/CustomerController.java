package com.example.conveniencestore.controller;

import com.example.conveniencestore.model.entity.Customer;
import com.example.conveniencestore.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
//    private Integer id;

    @GetMapping("")
    public String listAllCustomers(Model model) {
        List<Customer> Customers = customerService.getAllCustomers();
        model.addAttribute("customerinfo", Customers);
        return "customers/list";
    }

    @GetMapping("/create")
    public String createCustomerForm(Model model) {
        model.addAttribute("customerinfo", new Customer());
        return "customers/create";
    }

    @PostMapping("/create")
    public String handleCreateCustomerForm(@ModelAttribute Customer CustomerRequest) {
        Customer Customer = customerService.create(CustomerRequest);

        if(Customer == null){
            return "customers/createFail";
        }


        return "redirect:/customers";
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
        return "redirect:/customers";
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
        return "customers/detail_list";
    }
    @GetMapping ("/delete/{id}")
    public String deleteCustomer(@PathVariable Integer id) {
        customerService.delete(id);
        System.out.println("삭제완료");
        return "redirect:/customers";
    }
}
