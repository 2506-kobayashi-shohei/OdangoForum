package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.controller.form.UserManageForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import com.example.odango.forum.service.UserService;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        mav.addObject("changeStatus", status());
        setErrorMessage(mav);
        return mav;
    }

    private Map<Boolean, String> status () {
        Map<Boolean, String> map = new LinkedHashMap<>();
        map.put((Boolean) true, "稼働");
        map.put((Boolean) false, "停止");
        return map;
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
