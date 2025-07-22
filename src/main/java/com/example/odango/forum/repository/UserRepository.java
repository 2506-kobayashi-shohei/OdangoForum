package com.example.odango.forum.repository;

import com.example.odango.forum.repository.Entity.UserManage;
import com.example.odango.forum.repository.Entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserRepository {
    @Select("SELECT u.id, u.account, u.name, u.branch_id as branchId, u.department_id as departmentId, u.is_stopped as isStopped, " +
            "b.name as branchName, d.name as departmentName " +
            "FROM Users u INNER JOIN Branches b ON u.branch_id = b.id " +
            "INNER JOIN Departments d ON u.department_id = d.id")
    public List<UserManage> findAll();

    @Select("SELECT * FROM users WHERE account = #{account} AND password = #{password}")
    public List<User> selectByAccountAndPassword(String account, String password);

    @Select("SELECT * FROM users WHERE account = #{account}")
    public List<User> findByAccount(String account);

    @Insert("INSERT INTO users(account, password, name, branch_id, department_id) " +
            "VALUES(#{account}, #{password}, #{name}, #{branchId}, #{departmentId})")
    public void insert(User user);

    /* 編集画面でユーザー情報取得 */
    @Select("SELECT u.id, u.account, u.password, u.name, " +
            "u.branch_id as branchId, u.department_id as departmentId, u.is_stopped as isStopped " +
            "FROM Users u WHERE id = 1")
    public List<User> findById(int id);

    /* ユーザー復活・停止 */
    @Update("UPDATE users " +
            "SET is_stopped = #{status}, updated_date = CURRENT_TIMESTAMP " +
            "WHERE id = #{id}")
    void updateStatusById(Integer id, boolean status);
}
