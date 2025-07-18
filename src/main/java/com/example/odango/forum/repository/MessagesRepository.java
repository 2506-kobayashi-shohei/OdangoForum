package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.Messages;
import org.apache.ibatis.annotations.*;

@Mapper
public interface MessagesRepository {
    @Insert("INSERT INTO messages (title, text, category, user_id)" +
            "VALUES (#{title}, #{text}, #{category}, #{userId})")
    void insert(Messages message);
}
