package com.projectfinal.project.repository;

import com.projectfinal.project.model.StaffDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface StaffDetailRepository extends JpaRepository<StaffDetail,Integer> {

    @Query(value = "SELECT * FROM staff_detail WHERE id = ?1", nativeQuery = true)
    StaffDetail findById(int id);

    @Query(value = "SELECT * FROM staff_detail WHERE staff_id = ?1",nativeQuery = true)
    StaffDetail findByStaffId(String staff_id);

    @Query(value = "SELECT * FROM staff_detail WHERE staff_first_name LIKE %?1% OR staff_last_name LIKE %?1%", nativeQuery = true)
    List<StaffDetail> findByStaffName(String staff_name);

    @Query(value = "INSERT INTO staff_detail (staff_id,staff_first_name,staff_last_name,address,phone,avatar,create_at,update_at) values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    StaffDetail insertStaffDetail(StaffDetail staffDetail);

    @Query(value = "UPDATE staff_detail SET staff_id = ?1, staff_first_name = ?2, staff_last_name = ?3, address = ?4, phone = ?5, avatar = ?6, update_at = ?7 WHERE id = ?8", nativeQuery = true)
    StaffDetail updateStaffDetail(String staff_id, String staff_first_name, String staff_last_name, String address, String phone, String avatar, Timestamp update_at, int id);

}
