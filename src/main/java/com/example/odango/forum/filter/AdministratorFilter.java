package com.example.odango.forum.filter;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.repository.Entity.User;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

public class AdministratorFilter implements Filter {
    //１．セッションからログインユーザ情報を取得する。
    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {

        //型変換
        HttpServletRequest httpReq = (HttpServletRequest) request;
        HttpServletResponse httpRes = (HttpServletResponse) response;

        HttpSession session = httpReq.getSession();
        UserForm user = (UserForm) session.getAttribute("loginUser");

        if (user.getBranchId() == 1) {
            chain.doFilter(request, response);
        } else {
            session.setAttribute("errorMessages", "無効なアクセスです");
            httpRes.sendRedirect("/Forum");
        }
    }

//    @Override
//    public void init(FilterConfig config) {
//    }

    @Override
    public void destroy() {
    }
}
