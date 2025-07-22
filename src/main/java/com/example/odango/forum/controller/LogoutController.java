package com.example.odango.forum.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class LogoutController {
    @Autowired
    HttpSession session;

    @GetMapping("/logout")
    public String logout() {
        session.getAttribute("loginUser");
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/Forum/login";
    }
}
