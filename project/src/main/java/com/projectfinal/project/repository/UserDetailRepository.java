package com.projectfinal.project.repository;

import com.projectfinal.project.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {

    @Query(value = "SELECT * FROM user_detail WHERE id = ?1", nativeQuery = true)
    UserDetail findById(int id);

    @Query(value = "SELECT * FROM user_detail WHERE member_id = ?1",nativeQuery = true)
    UserDetail findByUserId(String userId);

    @Query(value = "SELECT * FROM user_detail WHERE first_name LIKE %?1% OR last_name LIKE %?1%", nativeQuery = true)
    List<UserDetail> findByUsername(String userName);

    @Query(value = "INSERT INTO user_detail (member_id,first_name,last_name,dob,address,phone,avatar,create_at,update_at) values = ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8 ,?9", nativeQuery = true)
    UserDetail insertUserDetail(UserDetail userDetail);

    @Query(value = "UPDATE user_detail SET member_id = ?1, first_name = ?2, last_name = ?3, dob = ?4, address = ?5, phone = ?6, avatar = ?7, update_at = ?8 WHERE id = ?9", nativeQuery = true)
    UserDetail updateUserDetail(String memberId, String firstName, String lastName, Date dob, String address, String phone, String avatar, Timestamp update_at, int id);


}
