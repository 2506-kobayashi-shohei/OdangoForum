package com.example.odango.forum.mapper;

import com.example.odango.forum.repository.Entity.Users;

import java.util.List;

public interface UserMapper {
    Users getUser(int id);
    List<Users> getUserAll();
}
