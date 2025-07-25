package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.service.UserService;
import io.micrometer.common.util.StringUtils;
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

@Controller
public class SignUpController {
    @Autowired
    UserService userService;
    @Autowired
    HttpSession session;

    /*ユーザー新規登録画面表示*/
    @GetMapping("/Forum/management/new")
    ModelAndView newUser() {
        ModelAndView mav = new ModelAndView();
        UserForm userForm = new UserForm();
        mav.setViewName("/signUp");
        mav.addObject("confirmPassword", null);
        mav.addObject("formModel", userForm);
        return mav;
    }

    /*ユーザー新規登録処理*/
    @PostMapping("/Forum/management/signUp")
    ModelAndView signUp(@Validated(ValidationGroup.SignUp.class)
                        @ModelAttribute("formModel") UserForm userForm,
                        BindingResult result) {
        ModelAndView mav = new ModelAndView();
        /*アカウント重複チェック*/
        String account = userForm.getAccount();
        if (!StringUtils.isBlank(account)) {
            if (!userService.isUsersEmpty(account)) {
                FieldError notAccountUnique = new FieldError(result.getObjectName(), "account", account, false, null, null,
                        "アカウントが重複しています");
                result.addError(notAccountUnique);
            }
        }
        /*アカウント重複チェック（ここまで）*/
        if (result.hasErrors()) {
            mav.addObject("formModel", userForm);
            mav.setViewName("/signUp");
            return mav;
        }
        userService.insert(userForm);
        mav.setViewName("redirect:/Forum/management");
        return mav;
    }
}