package com.example.odango.forum.repository.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Comment {
    private int id;
    private String text;
    private int userId;
    private int messageId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
