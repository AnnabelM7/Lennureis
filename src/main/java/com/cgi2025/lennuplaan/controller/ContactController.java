package com.cgi2025.lennuplaan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

    @GetMapping("/contact")
    public String showContactPage() {
        return "contact"; //Viib contact.html faili
    }
}
