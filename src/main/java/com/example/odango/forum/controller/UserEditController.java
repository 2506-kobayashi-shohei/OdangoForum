package com.example.odango.forum.controller;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Controller
public class UserEditController {
    @Autowired
    HttpSession session;

    @Autowired
    UserService userService;

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
        UserForm loginUser = (UserForm) session.getAttribute("loginUser");
        mav.addObject("loginUser", loginUser);
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

    @PutMapping("/Forum/management/update/{id}")
    public ModelAndView updateUser(@PathVariable Integer id,
                                   @Validated(ValidationGroup.Edit.class)
                                   @ModelAttribute("editModel") UserForm userForm,
                                   BindingResult result){
        ModelAndView mav = new ModelAndView();
        List<String> errorMessages = new ArrayList<>();

        // エラー処理
        if (result.hasErrors()){
            for (FieldError error : result.getFieldErrors()) {
                errorMessages.add(error.getDefaultMessage());
            }
            // ユーザー編集画面にログインユーザー情報を加えたため追記
            UserForm loginUser = (UserForm) session.getAttribute("loginUser");
            mav.addObject("loginUser", loginUser);
            mav.addObject("errorMessages", errorMessages);
            mav.addObject("formModel", userForm);
            mav.setViewName("/userEdit");
            return mav;
        }

        // アカウント重複チェック
        if (!userService.isUnique(userForm.getAccount(), id)){
            errorMessages.add("アカウントが重複しています");
            mav.addObject("errorMessages", errorMessages);
            mav.addObject("formModel", userForm);
            mav.setViewName("/userEdit");
            return mav;
        }

        // アカウント編集処理
        userService.update(userForm);

        return new ModelAndView("redirect:/Forum/management");
    }
}
