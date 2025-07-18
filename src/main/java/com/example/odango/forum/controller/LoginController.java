package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UsersForm;
import com.example.odango.forum.service.UsersService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    UsersService usersService;
    @Autowired
    HttpSession session;

    @GetMapping("/Forum/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        UsersForm user = new UsersForm();
        mav.setViewName("/login");
        mav.addObject("loginModel", user);
        return mav;
    }

    @PostMapping("/Forum/tryLogin")
    public ModelAndView tryLogin(@Validated @ModelAttribute("loginModel") UsersForm userForm,
                                 BindingResult result) {
        ModelAndView mav = new ModelAndView();
        List<String> messages = new ArrayList<>();
        if(result.hasErrors()){
            for (FieldError error : result.getFieldErrors()) {
                messages.add(error.getDefaultMessage());
            }
            mav.addObject("errorMessages", messages);
            mav.setViewName("/login");
            return mav;
        }
        UsersForm user = usersService.select(userForm.getAccount(),userForm.getPassword());
        if(user == null){
            messages.add("ログインに失敗しました");
            mav.addObject("errorMessages", messages);
            mav.setViewName("/login");
            return mav;
        }
        session.setAttribute("loginUser", user);
        return new ModelAndView("redirect:/Forum");
    }
}
