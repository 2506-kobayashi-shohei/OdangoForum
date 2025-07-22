package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {
    @Select("SELECT * FROM users WHERE account = #{account} AND password = #{password}")
    public List<User> selectByAccountAndPassword(String account, String password);
}
