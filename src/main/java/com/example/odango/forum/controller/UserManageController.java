package com.example.odango.forum.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    /* ユーザー管理画面表示 */
//    @GetMapping("/Forum/userManage")
//    public ModelAndView userManage() {
//        // 管理者権限フィルター
//
//        // ユーザー情報全件取得
//        ModelAndView mav = new ModelAndView();
//        List<UsersForm> userData = userService.findAllUser();
//        // ユーザー情報をリクエストに設定し、フォワード処理
//
//        mav.setViewName("/userManage");
//        mav.addObject("users", userData);
//        return mav;
//    }

}
