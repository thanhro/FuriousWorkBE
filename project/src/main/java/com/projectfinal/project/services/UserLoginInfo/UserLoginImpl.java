package com.projectfinal.project.services.UserLoginInfo;

import com.projectfinal.project.model.UserLogin;
import com.projectfinal.project.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class UserLoginImpl implements IUserLogin {

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Override
    public List<UserLogin> findAllUserLoginInfo() {
        List<UserLogin> findAllUserLoginInfo = userLoginRepository.findAll();
        return findAllUserLoginInfo;
    }

    @Override
    public UserLogin findById(int id) {
        UserLogin findById = userLoginRepository.findById(id);
        return findById;
    }

    @Override
    public List<UserLogin> findByRoleId(int roleId) {
        List<UserLogin> findByRoleId = userLoginRepository.findByRoleId(roleId);
        return findByRoleId;
    }

    @Override
    public List<UserLogin> findByStatus(int status) {
        List<UserLogin> findByStatus = userLoginRepository.findByStatus(status);
        return findByStatus;
    }

    @Override
    public UserLogin insertUserLogin(UserLogin userLogin) {
        return userLoginRepository.insertUserLogin(userLogin);
    }

    @Override
    public UserLogin updateUserLogin(String email, String password, int role_id, int status, Timestamp update_at, int id) {
        return userLoginRepository.updateUserLogin(email,password,role_id,status,update_at,id);
    }
}
