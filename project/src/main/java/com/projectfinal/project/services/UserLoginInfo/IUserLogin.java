package com.projectfinal.project.services.UserLoginInfo;

import com.projectfinal.project.model.UserLogin;

import java.sql.Timestamp;
import java.util.List;

public interface IUserLogin {

    List<UserLogin> findAllUserLoginInfo();

    UserLogin findById(int id);

    List<UserLogin> findByRoleId(int roleId);

    List<UserLogin> findByStatus(int status);

    UserLogin insertUserLogin(UserLogin userLogin);

    UserLogin updateUserLogin(String email, String password, int role_id, int status, Timestamp update_at, int id);
}
