package com.example.odango.forum.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

public class LogoutController {
    @Autowired
    HttpSession session;

    @GetMapping("/Forum/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        //session.getAttribute("loginUser", user);
        // セッションを無効にする
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }
}
