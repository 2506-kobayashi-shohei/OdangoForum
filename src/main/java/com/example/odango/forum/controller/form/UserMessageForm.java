package com.example.odango.forum.controller.form;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserMessageForm {
    private int id;
    private String account;
    private String name;
    private int branchId;
    private int departmentId;
    private int userId;
    private String title;
    private String text;
    private String category;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
