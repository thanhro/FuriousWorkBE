package com.projectfinal.project.services.StaffLoginInfo;

import com.projectfinal.project.model.StaffLogin;

import java.sql.Timestamp;
import java.util.List;

public interface IStaffLogin {

    List<StaffLogin> findAllStaffLoginInfo();

    StaffLogin findById(int id);

    List<StaffLogin> findByRoleId(int roleId);

    List<StaffLogin> findByStatus(int status);

    StaffLogin insertStaffLogin(StaffLogin staffLogin);

    StaffLogin updateStaffLogin(String email, String password, int role_id, int company_id, int status, Timestamp update_at, int id);

}
