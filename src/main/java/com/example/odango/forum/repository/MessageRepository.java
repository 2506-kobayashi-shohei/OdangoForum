package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.Message;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MessageRepository {
    @Insert("INSERT INTO messages (title, text, category, user_id)" +
            "VALUES (#{title}, #{text}, #{category}, #{userId})")
    void insert(Message message);
}
