package com.example.odango.forum.repository.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserComment {
    private int id;
    private String account;
    private String name;
    private int branchId;
    private int departmentId;
    private int userId;
    private int messageId;
    private String text;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
