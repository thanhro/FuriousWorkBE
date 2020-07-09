package com.projectfinal.project.services.UserDetail;

import com.projectfinal.project.model.UserDetail;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IUserDetail {

    List<UserDetail> findAllUserDetail();

    UserDetail findById(int id);

    UserDetail findByUserId(String userId);

    List<UserDetail> findByUsername(String userName);

    UserDetail insertUserDetail(UserDetail userDetail);

    UserDetail updateUserDetail(String memberId, String firstName, String lastName, Date dob, String address, String phone, String avatar, Timestamp update_at, int id);

}
