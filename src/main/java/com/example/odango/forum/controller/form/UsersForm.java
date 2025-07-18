package com.example.odango.forum.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UsersForm {
    private int id;
    @NotBlank(message = "アカウントを入力してください")
    private String account;
    @NotBlank(message = "パスワードを入力してください")
    private String password;
    @NotBlank(message = "名前を入力してください")
    private String name;
    private int branchId;
    private int departmentId;
    private boolean isStoped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
