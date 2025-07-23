package com.example.odango.forum.controller.form;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.*;
import jakarta.validation.groups.Default;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MessageForm {
    private int id;

    @Size(max = 30, message = "件名は30文字以内で入力してください")
    private String title;
    /*@NotBlankの強化版*/
    @AssertFalse(message = "件名を入力してください")
    public boolean isTitleBlank() {
        if (StringUtils.isBlank(title)) {
            return true;
        }
        return title.matches("^[\\s　]+$");
    }

    @Size(max = 1000, message = "本文は1000文字以内で入力してください")
    private String text;

    @AssertFalse(message = "本文を入力してください")
    public boolean isTextBlank() {
        if (StringUtils.isBlank(text)) {
            return true;
        }
        return text.matches("^[\\s　]+$");
    }

    @Size(max = 10, message = "カテゴリは10文字以内で入力してください")
    private String category;

    @AssertFalse(message = "カテゴリを入力してください")
    public boolean isCategoryBlank() {
        if (StringUtils.isBlank(category)) {
            return true;
        }
        return category.matches("^[\\s　]+$");
    }

    private int userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}