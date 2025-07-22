package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.MessageForm;
import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class MessageController {
    @Autowired
    MessageService messageService;
    @Autowired
    HttpSession session;

    /*新規投稿画面表示*/
    @GetMapping("/Forum/new")
    public ModelAndView newContent() {
        ModelAndView mav = new ModelAndView();
        // form用の空のentityを準備
        MessageForm messageForm = new MessageForm();
        // 画面遷移先を指定
        mav.setViewName("/new");
        // 準備した空のFormを保管
        mav.addObject("formModel", messageForm);
        return mav;
    }
    /*新規投稿登録処理*/
    @PostMapping("/Forum/add")
    public ModelAndView addMessage(@Validated @ModelAttribute("formModel") MessageForm messageForm,
                                   BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("/new");
        }
        UserForm user = (UserForm) session.getAttribute("loginUser");
        // 投稿をテーブルに格納
        messageService.addMessage(messageForm, user.getId());
        // rootへリダイレクト
        return new ModelAndView("redirect:/Forum");
    }
    /*削除処理*/
    @DeleteMapping("/Forum/deleteMessage/{id}")
    public ModelAndView deleteMessage(@PathVariable Integer id){
        MessageForm message = messageService.findMessage(id);
        UserForm user = (UserForm) session.getAttribute("loginUser");
        if(user.getId() != message.getUserId()){
            return new ModelAndView("redirect:/Forum");
        }
        messageService.delete(id);
        return new ModelAndView("redirect:/Forum");
    }
}
