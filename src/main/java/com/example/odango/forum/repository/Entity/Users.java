package com.example.odango.forum.repository.Entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Users {
    /*@Id @ColumnなどのアノテーションはJPA用だった。
    * 今回はMybatisと最初から決めていたので不要らしい*/
    private int id;
    private String account;
    private String password;
    private String name;
    private int branchId;
    private int departmentId;
    private boolean isStoped;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
}
