package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.UserMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMessageRepository {
    @Select("SELECT m.id, m.title, m.text, m.category, m.user_id, m.created_date, m.updated_date, " +
            "u.account, u.name, u.branch_id, u.department_id " +
            "FROM Message m INNER JOIN User u ON m.user_id = u.id " +
            "WHERE m.created_date BETWEEN #{start} AND #{end} " +
            "ORDER BY m.created_date DESC LIMIT #{limit}")
    List<UserMessage> selectAll(LocalDateTime startDate, LocalDateTime endDate, Integer limit);

    @Select("SELECT m.id, m.title, m.text, m.category, m.user_id, m.created_date, m.updated_date, " +
            "u.account, u.name, u.branch_id, u.department_id " +
            "FROM Message m INNER JOIN User u ON m.user_id = u.id " +
            "WHERE m.created_date BETWEEN #{start} AND #{end} " +
            "AND m.category LIKE #{category} " +
            "ORDER BY m.created_date DESC LIMIT #{limit}")
    List<UserMessage> selectByCategory(LocalDateTime startDate, LocalDateTime endDate, String category, Integer limit);
}
