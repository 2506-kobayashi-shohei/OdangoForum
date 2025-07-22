package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentRepository {
    @Insert("INSERT INTO comments (text, user_id, message_id) " +
            "VALUES (#{text}, #{userId}, #{messageId});")
    void insert(Comment comment);
}
