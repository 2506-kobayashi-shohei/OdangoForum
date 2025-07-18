package com.example.odango.forum.service;

import com.example.odango.forum.controller.form.UsersForm;
import com.example.odango.forum.repository.Entity.Users;
import com.example.odango.forum.repository.UsersRepository;
import com.example.odango.forum.utils.CipherUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsersService {
    @Autowired
    UsersRepository usersRepository;
    /* レコード全件取得処理 */
    public List<UsersForm> findAllUser() {
        List<Users> results = usersRepository.getUserAll();
        List<UsersForm> users = setUsersForm(results);
        return users;
    }

    /* DBから取得したデータをFormに設定 */
    private List<UsersForm> setUsersForm(List<Users> results) {
        List<UsersForm> users = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            UsersForm userForm = new UsersForm();
            Users result = results.get(i);
            userForm.setId(result.getId());
            userForm.setAccount(result.getAccount());
            userForm.setPassword(result.getPassword());
            userForm.setName(result.getName());
            userForm.setBranchId(result.getBranchId());
            userForm.setDepartmentId(result.getDepartmentId());
            userForm.setStoped(result.isStoped());
            users.add(userForm);
        }
        return users;
    }

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
        userForm.setStoped(user.isStoped());
        userForm.setCreatedDate(user.getCreatedDate());
        userForm.setUpdatedDate(user.getUpdatedDate());
        return userForm;
    }
}
