package com.example.odango.forum.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentForm {
    private int id;

    @NotBlank(message ="メッセージを入力してください")
    @Size(max = 500, message = "500文字以内で入力してください")
    private String text;
    private int userId;
    private int messageId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
