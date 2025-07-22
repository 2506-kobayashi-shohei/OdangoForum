package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserRepository {
    @Select("SELECT * FROM users WHERE account = #{account} AND password = #{password}")
    public List<User> selectByAccountAndPassword(String account, String password);

    @Select("SELECT * FROM users WHERE account = #{account}")
    public List<User> findByAccount(String account);

    @Insert("INSERT INTO users(account, password, name, branch_id, department_id) " +
            "VALUES(#{account}, #{password}, #{name}, #{branchId}, #{departmentId})")
    public void insert(User user);
}
