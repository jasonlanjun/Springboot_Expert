package com.junlan.controller;

import com.junlan.domain.ContactEntity;
import com.junlan.service.ContactServiceInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.DataSource;

@Controller
public class HomeController {

    @Autowired
    DataSource dataSource;
    @Autowired
    private ContactServiceInt contactService;

    @GetMapping("/")
    public String home() {
        return "Home";
    }

    @GetMapping("/ourproducts")
    public String ourproducts() {
        return "Error";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        model.addAttribute("contact", new ContactEntity());
        return "ContactForm";
    }

    @PostMapping("/savecontact")
    public String saveContact(ContactEntity contactData, BindingResult result) {
        contactService.saveContactUser(contactData);
        return "redirect:/contact";
    }

    @GetMapping("/login")
    public String login() {
        return "Login";
    }

    @GetMapping("/resource")
    public String resource() {
        return "Resource";
    }

    @GetMapping("/services")
    public String services() {
        return "Services";
    }

    @PostMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
