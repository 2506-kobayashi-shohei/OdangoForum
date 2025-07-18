package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UserMessageForm;
import com.example.odango.forum.controller.form.UsersForm;
import com.example.odango.forum.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class TopController {
    @Autowired
    HttpSession session;

    @Autowired
    MessageService messageService;

    @GetMapping("/Forum")
    public ModelAndView top(@RequestParam(name = "start", required = false) LocalDateTime start,
                            @RequestParam(name = "end", required = false) LocalDateTime end,
                            @RequestParam(name = "category", required = false) String category){
        ModelAndView mav = new ModelAndView();
        boolean button = false; // ボタン表示フラグ
        UsersForm loginUser = (UsersForm)session.getAttribute("loginUser");

        // ログインユーザ情報チェック
        if (loginUser.getDepartmentId() == 1){
            button = true;
        }

        // 投稿情報取得
        List<UserMessageForm> messages = messageService.findAllMessage(start, end, category);
        // コメント情報取得
        List<UserCommentForm> comments = commentService.findAllComment();

        mav.setViewName("/top");
        mav.addObject("messages",messages);
        mav.addObject("comments",comments);
        mav.addObject("button",button);
        return mav;
    }
}
