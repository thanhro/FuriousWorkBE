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
    public UserDetail findByUserId(String userId) {
        UserDetail findByUserId = userDetailRepository.findByUserId(userId);
        return findByUserId;
    }

    @Override
    public List<UserDetail> findByUsername(String userName) {
        List<UserDetail> findByUsername = userDetailRepository.findByUsername(userName);
        return findByUsername;
    }

    @Override
    public UserDetail insertUserDetail(UserDetail userDetail) {
        return userDetailRepository.insertUserDetail(userDetail);
    }

    @Override
    public UserDetail updateUserDetail(String memberId, String firstName, String lastName, Date dob, String address, String phone, String avatar, Timestamp update_at, int id) {
        return userDetailRepository.updateUserDetail(memberId,firstName,lastName,dob,address,phone,avatar,update_at,id);
    }
}
