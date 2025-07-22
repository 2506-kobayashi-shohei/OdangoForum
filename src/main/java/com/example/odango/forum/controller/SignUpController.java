package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class SignUpController {
    @Autowired
    UserService userService;

    /*ユーザー新規登録画面表示*/
    @GetMapping("/Forum/management/new")
    ModelAndView newUser(){
        ModelAndView mav = new ModelAndView();
        UserForm userForm = new UserForm();
        mav.setViewName("/signUp");
        mav.addObject("formModel", userForm);
        return mav;
    }
    /*ユーザー新規登録処理*/
    @PostMapping("/Forum/management/signUp")
    ModelAndView signUp(@Validated @ModelAttribute("formModel") UserForm userForm,
                        BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("/Forum/management/new");
        }
        userService.insert(userForm);
        return new ModelAndView("redirect:/Forum/management");
    }
}