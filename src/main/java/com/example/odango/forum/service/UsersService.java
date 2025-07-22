package com.example.odango.forum.service;

import com.example.odango.forum.controller.form.UsersForm;
import com.example.odango.forum.repository.Entity.Users;
import com.example.odango.forum.repository.UsersRepository;
import com.example.odango.forum.utils.CipherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public UsersForm select(String account, String password) {
        /*String encPassword = CipherUtil.encrypt(password);
        * ユーザー情報登録処理が実装できたらエンコーディングします*/
        List<Users> users = usersRepository.selectByAccountAndPassword(account, /*encPassword*/password);
        if(users.isEmpty()){
            return null;
        }
        return setUsersForm(users.get(0));
    }

    private UsersForm setUsersForm(Users user) {
        UsersForm userForm = new UsersForm();
        userForm.setId(user.getId());
        userForm.setAccount(user.getAccount());
        userForm.setPassword(user.getPassword());
        userForm.setName(user.getName());
        userForm.setBranchId(user.getBranchId());
        userForm.setDepartmentId(user.getDepartmentId());
        userForm.setStopped(user.isStopped());
        userForm.setCreatedDate(user.getCreatedDate());
        userForm.setUpdatedDate(user.getUpdatedDate());
        return userForm;
    }
}
