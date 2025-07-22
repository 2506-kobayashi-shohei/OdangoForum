package com.example.odango.forum.service;

import com.example.odango.forum.controller.form.UserForm;
import com.example.odango.forum.repository.Entity.User;
import com.example.odango.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserForm select(String account, String password) {
        /*String encPassword = CipherUtil.encrypt(password);
        * ユーザー情報登録処理が実装できたらエンコーディングします*/
        List<User> users = userRepository.selectByAccountAndPassword(account, /*encPassword*/password);
        if(users.isEmpty()){
            return null;
        }
        return setUsersForm(users.get(0));
    }

    private UserForm setUsersForm(User user) {
        UserForm userForm = new UserForm();
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

    public void insert(UserForm userForm){
        User user = setUserEntity(userForm);
        userRepository.insert(user);
    }
    private User setUserEntity(UserForm userForm){
        User user =new User();
        user.setId(userForm.getId());
        user.setAccount(userForm.getAccount());
        user.setPassword(userForm.getPassword());
        user.setName(userForm.getName());
        user.setBranchId(userForm.getBranchId());
        user.setDepartmentId(userForm.getDepartmentId());
        user.setStopped(userForm.isStopped());
        user.setCreatedDate(userForm.getCreatedDate());
        user.setUpdatedDate(userForm.getUpdatedDate());
        return user;
    }

    public boolean isUnique(String account){
        List<User> users = userRepository.findByAccount(account);
        return users.isEmpty();
    }
}
