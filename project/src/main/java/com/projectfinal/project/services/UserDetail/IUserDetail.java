package com.projectfinal.project.services.UserDetail;

import com.projectfinal.project.model.UserDetail;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IUserDetail {

    List<UserDetail> findAllUserDetail();

    UserDetail findById(int id);

    UserDetail findByUserId(String user_id);

    List<UserDetail> findByUsername(String user_name);

    UserDetail insertUserDetail(UserDetail userDetail);

    UserDetail updateUserDetail(String member_id, String first_name, String last_name, Date dob, String address, String phone, String avatar, Timestamp update_at, int id);

}
