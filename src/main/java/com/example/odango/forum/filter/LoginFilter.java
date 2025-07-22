package com.example.odango.forum.filter;
import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.repository.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoginFilter implements Filter {
    @Autowired
    HttpSession httpSession;

    //１．セッションからログインユーザ情報を取得する。
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

//    @Override
//    public void init(FilterConfig config) {
//    }

    @Override
    public void destroy() {
    }
}