package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.controller.form.UserManageForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.odango.forum.service.UserService;

import java.util.List;

@Controller
public class UserManageController {
    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    @GetMapping("/Forum/management")
    public ModelAndView userManage() {
        ModelAndView mav = new ModelAndView();
        List<UserManageForm> userData = userService.findAllUser();
        UserForm loginUser = (UserForm) session.getAttribute("loginUser");
        mav.setViewName("/management");
        mav.addObject("loginUser", loginUser);
        mav.addObject("users", userData);
        setErrorMessage(mav);
        return mav;
    }

    /* ユーザー復活・停止 */
    @PutMapping("/Forum/changeStatus/{id}")
    public ModelAndView changeStatus(@PathVariable Integer id, @RequestParam(name = "status", required = false) boolean status) {
        userService.changeStatus(id, status);
        return new ModelAndView("redirect:/Forum/management");
    }

    /* エラーメッセージ取得 */
    private void setErrorMessage(ModelAndView mav) {
        if (session.getAttribute("errorMessages") != null) {
            mav.addObject("errorMessages", session.getAttribute("errorMessages"));
            // sessionの破棄
            session.removeAttribute("errorMessages");
        }
    }

}
