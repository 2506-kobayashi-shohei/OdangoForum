package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.MessagesForm;
import com.example.odango.forum.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {
    @Autowired
    MessagesService messagesService;

    /*新規投稿画面表示*/
    @GetMapping("/Forum/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        MessagesForm messageForm = new MessagesForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", messageForm);
        return mav;
    }
    /*新規投稿登録処理*/
    @PostMapping("/Forum/add")
    public ModelAndView addMessage(@Validated @ModelAttribute("formModel") MessagesForm messageForm,
                                   BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("/new");
        }
        // 投稿をテーブルに格納
        messagesService.addMessage(messageForm);
        // rootへリダイレクト
        return new ModelAndView("redirect:/Forum");
    }
}
