package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.Message;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface MessageRepository {
    @Insert("INSERT INTO messages (title, text, category, user_id)" +
            "VALUES (#{title}, #{text}, #{category}, #{userId})")
    void insert(Message message);

    @Delete("DELETE FROM messages WHERE id = #{id}")
    void delete(Integer id);
}
