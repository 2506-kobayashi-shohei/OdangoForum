package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.util.StringUtils.hasText;

@Controller
public class UserEditController {
    @Autowired
    UserService userService;

    @Autowired
    HttpSession session;

    /* 編集画面表示 */
    @GetMapping({"/Forum/management/edit","/Forum/management/edit/","/Forum/management/edit/{id}"})
    public ModelAndView editTask(@PathVariable(required = false) String id) {
        // 編集画面エラーで戻す場合は入力内容を返す
        if (this.session.getAttribute("formModel") != null){
            ModelAndView mav = new ModelAndView();
            mav.addObject("formModel", this.session.getAttribute("formModel"));
            setErrorMessage(mav);
            mav.setViewName("/userEdit");
            return mav;
        }

        UserForm user = null;

        if (hasText(id) && (id.matches("^[0-9]+$"))) {
            Integer taskId = Integer.valueOf(id);
            user = userService.editUser(taskId);
        }

        if (user == null) {
            session.setAttribute("errorMessages", "不正なパラメータが入力されました");
            return new ModelAndView("redirect:/Forum/management");
        }

        ModelAndView mav = new ModelAndView();
        mav.addObject("formModel", user);
        setErrorMessage(mav);
        mav.setViewName("/userEdit");
        return mav;
    }

    /* エラーメッセージ取得 */
    private void setErrorMessage(ModelAndView mav) {
        if (session.getAttribute("errorMessages") != null) {
            mav.addObject("errorMessages", session.getAttribute("errorMessages"));
            // sessionの破棄
            session.removeAttribute("errorMessages");
        }
    }
}
