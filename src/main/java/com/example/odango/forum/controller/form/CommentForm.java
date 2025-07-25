package com.example.odango.forum.controller.form;

import io.micrometer.common.util.StringUtils;
import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentForm {
    private int id;

    @Size(max = 500, message = "500文字以内で入力してください")
    private String text;

    /*NotBlankの強化版*/
    @AssertFalse(message = "メッセージを入力してください")
    public boolean isBlank() {
        if (StringUtils.isBlank(text)) {
            return true;
        }
        return text.matches("^[\\s　]+$");
    }

    private int userId;
    private int messageId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
