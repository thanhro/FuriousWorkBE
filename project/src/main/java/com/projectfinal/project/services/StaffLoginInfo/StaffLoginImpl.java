package com.projectfinal.project.services.StaffLoginInfo;

import com.projectfinal.project.model.StaffLogin;
import com.projectfinal.project.repository.StaffLoginRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StaffLoginImpl implements IStaffLogin {

    private StaffLoginRepository staffLoginRepository;

    @Override
    public List<StaffLogin> findAllStaffLoginInfo() {
        List<StaffLogin> findAllStaffLoginInfo = staffLoginRepository.findAll();
        return findAllStaffLoginInfo;
    }

    @Override
    public StaffLogin findStaffLoginInfoById(String id) {
        StaffLogin findStaffLoginInfoById = staffLoginRepository.findStaffLoginInfoById(id);
        return findStaffLoginInfoById;
    }

    @Override
    public List<StaffLogin> findByRoleId(int role_id) {
        List<StaffLogin> findByRoleId = staffLoginRepository.findByRoleId(role_id);
        return findByRoleId;
    }

    @Override
    public List<StaffLogin> findByStatus(int status) {
        List<StaffLogin> findByStatus = staffLoginRepository.findByStatus(status);
        return findByStatus;
    }


    @Override
    public StaffLogin updateStaffLogin(String email, String password, int role_id, int company_id, int status, Timestamp update_at, int id) {
        return staffLoginRepository.updateStaffLogin(email,password,role_id,company_id,status,update_at,id);
    }
}
