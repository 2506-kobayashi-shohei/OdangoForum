package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.UserComment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserCommentRepository {
    @Select("SELECT c.id, c.text, c.user_id, c.message_id, c.created_date, c.updated_date, " +
            "u.account, u.name, u.branch_id, u.department_id " +
            "FROM comments c INNER JOIN users u ON c.user_id = u.id " +
            "ORDER BY c.created_date ASC")
    public List<UserComment> findAll();
}
