package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserEditController {
    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

    // URLはフロント側に併せる
    @PutMapping("/Forum/UpdateUser/{id}")
    public ModelAndView updateUser(@PathVariable Integer id,
                                   @Validated @ModelAttribute("editModel") UserForm userForm,
                                   BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();

        // エラー処理
        if (result.hasErrors()){
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            mav.addObject("errorMessages", errorMessages);
            mav.addObject("commentForm", userForm);
            mav.setViewName("/Forum/EditUser/{id}");
            return mav;
        }

        // アカウント重複チェック
        if (userService.isUnique(userForm.getAccount())){
            errorMessages.add("アカウントが重複しています");
            mav.addObject("errorMessages", errorMessages);
            mav.addObject("commentForm", userForm);
            mav.setViewName("/Forum/EditUser/{id}");
            return mav;
        }

        // アカウント編集処理
        userService.update(userForm);

        return new ModelAndView("redirect:/Forum/management");
    }
}
