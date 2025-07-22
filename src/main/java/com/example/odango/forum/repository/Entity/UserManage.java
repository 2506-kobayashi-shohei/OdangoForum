package com.example.odango.forum.repository.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserManage {
    private int id;
    private String account;
    private String name;
    private int branchId;
    private int departmentId;
    private String branchName;
    private String departmentName;
    private boolean isStopped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
