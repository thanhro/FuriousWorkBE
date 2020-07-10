package com.projectfinal.project.services.StaffDetail;

import com.projectfinal.project.model.StaffDetail;

import java.sql.Timestamp;
import java.util.List;

public interface IStaffDetail {

    List<StaffDetail> findAllStaffDetail();

    StaffDetail findById(int id);

    StaffDetail findByStaffId(String staff_id);

    List<StaffDetail> findByStaffName(String staff_name);

    StaffDetail insertStaffDetail(StaffDetail staffDetail);

    StaffDetail updateStaffDetail(String staff_id, String staff_first_name, String staff_last_name, String address, String phone, String avatar, Timestamp update_at, int id);
}
