package com.example.odango.forum.controller.form;

import com.example.odango.forum.service.UserService;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class UserForm {
    private int id;

    @NotBlank(message = "アカウントを入力してください")
    @Size(min = 6, max = 20, message = "アカウントは半角英数字かつ6文字以上20文字以下で入力してください")
    private String account;

    // 全角スペースのみが入力された時のバリデーション
    @AssertTrue(message = "アカウントは半角英数字かつ6文字以上20文字以下で入力してください")
    public boolean isValidAccount() {
        if (account.matches("^[　]+$")) {
            return false;
        } else {
            return true;
        }
    }

    @NotBlank(message = "パスワードを入力してください")
    @Size(min = 6, max = 20, message = "パスワードは半角文字かつ6文字以上20文字以下で入力してください")
    private String password;

    // 全角スペースのみが入力された時のバリデーション
    @AssertTrue(message = "パスワードは半角文字かつ6文字以上20文字以下で入力してください")
    public boolean isValidPassword() {
        if (password.matches("^[　]+$")) {
            return false;
        } else {
            return true;
        }
    }

    private String confirmPassword;

    /*パスワードと確認用パスワードの一致判定メソッド
     * @Validated によって@AssertTrueがついたメソッドを呼び出すことができる*/
    //@AssertTrue(message = "パスワードと確認用パスワードが一致しません")
    public boolean isPasswordValid() {
        return password.equals(confirmPassword);
    }

    @NotBlank(message = "氏名を入力してください")
    @Size(max = 10, message = "氏名は10文字以下で入力してください")
    private String name;

    @NotNull(message = "支社を選択してください")
    private Integer branchId;

    @NotNull(message = "部署を選択してください")
    private Integer departmentId;

    /*支社と部署の組み合わせ判定メソッド
    * @Validated によって@AssertTrueがついたメソッドを呼び出すことができる*/
    @AssertTrue(message = "支社と部署の組み合わせが不正です")
    public boolean isMatchIds(){
        Map<Integer,List<Integer>> combination = new HashMap<Integer, List<Integer>>();
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
