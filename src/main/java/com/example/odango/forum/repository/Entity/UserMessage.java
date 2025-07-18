package com.example.odango.forum.repository.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class UserMessage {
    @Id
    @Column
    private int id;

    @Column
    private String account;

    @Column
    private String name;

    @Column(name = "branch_id")
    private int branchId;

    @Column(name = "department_id")
    private int departmentId;

    @Column(name = "user_id")
    private int userId;

    @Column
    private String title;

    @Column
    private String text;

    @Column
    private String category;

    @Column(name = "created_date", insertable = false, updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "updated_date", insertable = false, updatable = true)
    private LocalDateTime updatedDate;
}