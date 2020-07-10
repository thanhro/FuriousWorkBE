package com.projectfinal.project.services.UserDetail;

import com.projectfinal.project.model.UserDetail;
import com.projectfinal.project.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserDetailImpl implements  IUserDetail{

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public List<UserDetail> findAllUserDetail() {
        List<UserDetail> findAllUserDetail = userDetailRepository.findAll();
        return findAllUserDetail;
    }

    @Override
    public UserDetail findById(int id) {
        UserDetail findById = userDetailRepository.findById(id);
        return findById;
    }

    @Override
    public UserDetail findByUserId(String user_id) {
        UserDetail findByUserId = userDetailRepository.findByUserId(user_id);
        return findByUserId;
    }

    @Override
    public List<UserDetail> findByUsername(String user_name) {
        List<UserDetail> findByUsername = userDetailRepository.findByUsername(user_name);
        return findByUsername;
    }

    @Override
    public UserDetail insertUserDetail(UserDetail userDetail) {
        return userDetailRepository.insertUserDetail(userDetail);
    }

    @Override
    public UserDetail updateUserDetail(String member_id, String first_name, String last_name, Date dob, String address, String phone, String avatar, Timestamp update_at, int id) {
        return userDetailRepository.updateUserDetail(member_id,first_name,last_name,dob,address,phone,avatar,update_at,id);
    }
}
