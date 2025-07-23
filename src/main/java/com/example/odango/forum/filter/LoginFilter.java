package com.example.odango.forum.filter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class LoginFilter implements Filter {
    @Autowired
    HttpSession httpSession;

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //型変換
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        HttpSession session = httpReq.getSession();

        if (session.getAttribute("loginUser") != null) {
            chain.doFilter(request, response);
        } else {
            session.setAttribute("errorMessages", "ログインしてください");
            httpRes.sendRedirect("/Forum/login");
            return;
        }
    }

    @Override
    public void destroy() {
    }
}