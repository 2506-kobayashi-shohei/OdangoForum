package com.example.odango.forum.controller.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageForm {
    private int id;
    @NotBlank(message = "件名を入力してください")
    private String title;
    @NotBlank(message = "投稿内容を入力してください")
    private String text;
    @NotBlank(message = "カテゴリを入力してください")
    private String category;
    private int userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
