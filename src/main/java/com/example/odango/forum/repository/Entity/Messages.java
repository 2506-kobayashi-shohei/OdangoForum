package com.example.odango.forum.repository.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Messages {
    private int id;
    private String title;
    private String text;
    private String category;
    private int userId;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
