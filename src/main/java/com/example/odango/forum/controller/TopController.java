package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.*;
import com.example.odango.forum.service.CommentService;
import com.example.odango.forum.service.MessageService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
public class TopController {
    @Autowired
    HttpSession session;

    @Autowired
    MessageService messageService;

    @Autowired
    CommentService commentService;

    @GetMapping("/Forum")
    public ModelAndView top(@RequestParam(name = "start", required = false) LocalDate start,
                            @RequestParam(name = "end", required = false) LocalDate end,
                            @RequestParam(name = "category", required = false) String category) {
        ModelAndView mav = new ModelAndView();
        boolean button = false; // ボタン表示フラグ
        UserForm loginUser = (UserForm) session.getAttribute("loginUser");

        // ログインユーザ情報チェック
        if (loginUser.getDepartmentId() == 1) {
            button = true;
        }

        // 投稿情報取得
        List<UserMessageForm> messages = messageService.findAllMessage(start, end, category);

        // 表示用作成日設定
        List<UserMessageForm> processing = messageService.dateTimeFormatter(messages);

        // コメント情報取得
        List<UserCommentForm> comments = commentService.findAllComment();
        CommentForm commentForm = new CommentForm();
        mav.setViewName("/top");
        mav.addObject("start", start);
        mav.addObject("end", end);
        mav.addObject("category", category);
        mav.addObject("loginUser", loginUser);
        mav.addObject("messages", processing);
        mav.addObject("comments", comments);
        mav.addObject("commentForm", commentForm);
        mav.addObject("button", button);
        setErrorMessage(mav);
        return mav;
    }
    /* 管理者権限フィルターのエラーメッセージ取得 */
    private void setErrorMessage(ModelAndView mav) {
        if (session.getAttribute("errorMessages") != null) {
            mav.addObject("errorMessages", session.getAttribute("errorMessages"));
            // sessionの破棄
            session.removeAttribute("errorMessages");
        }
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    @ExceptionHandler(Exception.class)
    public ModelAndView handleError() {
        session.invalidate();
        session.setAttribute("errorMessages", "不正なパラメータを検知しました");
        return new ModelAndView("forward:/Forum/login");
    }
}
