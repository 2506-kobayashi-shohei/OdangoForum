package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.CommentForm;
import com.example.odango.forum.controller.form.UserCommentForm;
import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.service.CommentService;
import io.micrometer.common.util.StringUtils;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CommentController {
    @Autowired
    HttpSession session;

    @Autowired
    CommentService commentService;

    @PostMapping("/Forum/Comment")
    public ModelAndView addComment(@ModelAttribute("commentForm") @Validated CommentForm commentForm,
                                   BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();

        // エラー処理
        if (result.hasErrors()){
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            session.setAttribute("errorMessages", errorMessages);
            mav.addObject("commentForm", commentForm);
            mav.setViewName("redirect:/Forum");
            return mav;
        }

        // ユーザID取得
        UserForm loginUser = (UserForm)session.getAttribute("loginUser");
        commentForm.setUserId(loginUser.getId());

        // コメント登録処理
        commentService.saveComment(commentForm);

        return new ModelAndView("redirect:/Forum");
    }

    @DeleteMapping("/Forum/deleteComment/{id}")
    public ModelAndView deleteComment(@PathVariable Integer id){
        ModelAndView mav = new ModelAndView();
        String strId = String.valueOf(id);

        if(StringUtils.isBlank(strId) || !strId.matches("^[0-9]+$")){
            mav.addObject("errorMessages", "不正なパラメータが入力されました");
            mav.setViewName("redirect:/Forum");
            return mav;
        }

        UserCommentForm comment = commentService.findComment(id);
        UserForm user = (UserForm) session.getAttribute("loginUser");

        if(comment == null ||
                !(user.getId() == comment.getUserId() || user.getDepartmentId() == 2
                        || (user.getBranchId() == comment.getBranchId()
                        && user.getDepartmentId() == 3 && comment.getDepartmentId() == 4)
                )){
            mav.addObject("errorMessages", "不正なパラメータが入力されました");
            mav.setViewName("redirect:/Forum");
            return mav;
        }

        // コメント削除処理
        commentService.deleteComment(id);

        mav.setViewName("redirect:/Forum");
        return mav;
    }
}
