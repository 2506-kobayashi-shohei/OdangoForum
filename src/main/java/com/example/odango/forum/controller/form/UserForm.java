package com.example.odango.forum.controller.form;

import com.example.odango.forum.controller.ValidationGroup;
import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserForm {
    private int id;
    @NotBlank(message = "アカウントを入力してください",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Login.class, ValidationGroup.Edit.class})
    @Pattern(regexp = "^[a-zA-Z0-9]{6,20}$",
            message = "アカウントは半角英数字かつ6文字以上20文字以下で入力してください",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Edit.class})
    private String account;
    @NotBlank(message = "パスワードを入力してください",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Login.class})
    @Pattern(regexp = "^[!-~]{6,20}$",
            message = "パスワードは半角文字かつ6文字以上20文字以下で入力してください",
            groups = ValidationGroup.SignUp.class)
    private String password;
    private String confirmPassword;

    /*パスワードと確認用パスワードの一致判定メソッド
     * @Validated によって@AssertTrueがついたメソッドを呼び出すことができる*/
    @AssertTrue(message = "パスワードと確認用パスワードが一致しません",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Edit.class})
    public boolean isPasswordValid() {
        if (!StringUtils.isBlank(password) && !StringUtils.isBlank(confirmPassword)) {
            return password.equals(confirmPassword);
        } else if (StringUtils.isBlank(password) && StringUtils.isBlank(confirmPassword)) {
            return true;
        } else {
            return false;
        }
    }

    @NotBlank(message = "氏名を入力してください",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Edit.class})
    @Size(max = 10, message = "氏名は10文字以下で入力してください",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Edit.class})
    private String name;
    @NotNull(message = "支社を選択してください",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Edit.class})
    private Integer branchId;
    @NotNull(message = "部署を選択してください",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Edit.class})
    private Integer departmentId;

    /*支社と部署の組み合わせ判定メソッド
     * @Validated によって@AssertTrueがついたメソッドを呼び出すことができる*/
    @AssertTrue(message = "支社と部署の組み合わせが不正です",
            groups = {ValidationGroup.SignUp.class, ValidationGroup.Edit.class})
    public boolean isMatchIds() {
        if (branchId == null || departmentId == null) {
            return true;
        }
        Map<Integer, List<Integer>> combination = new HashMap<Integer, List<Integer>>();
        combination.put(1, List.of(1, 2));/*本社：総務人事部、情報管理部*/
        combination.put(2, List.of(3, 4));/*A社；営業部、技術部*/
        combination.put(3, List.of(3, 4));/*B社；営業部、技術部*/
        combination.put(4, List.of(3, 4));/*C社；営業部、技術部*/
        return combination.get(branchId).contains(departmentId);
    }

    private boolean isStopped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
