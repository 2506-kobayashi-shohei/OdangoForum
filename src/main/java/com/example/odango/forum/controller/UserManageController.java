package com.example.odango.forum.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.example.odango.forum.controller.form.UsersForm;
import com.example.odango.forum.service.UsersService;

import java.util.List;

@Controller
public class UserManageController {
    @Autowired
    UsersService userService;

    @Autowired
    HttpSession session;


    @RequestMapping(value = "/Forum/userManage", method = RequestMethod.GET)
    public ModelAndView userManage() {
        ModelAndView mav = new ModelAndView();
        List<UsersForm> userData = userService.findAllUser();
        mav.setViewName("/userManage");
        mav.addObject("users", userData);
        return mav;
    }

}
