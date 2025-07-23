package com.example.odango.forum.repository.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class User {
    private int id;
    private String account;
    private String password;
    private String name;
    private Integer branchId;
    private Integer departmentId;
    private boolean isStopped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}