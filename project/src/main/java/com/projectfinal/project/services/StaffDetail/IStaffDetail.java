package com.projectfinal.project.services.StaffDetail;

import com.projectfinal.project.model.StaffDetail;

import java.sql.Timestamp;
import java.util.List;

public interface IStaffDetail {

    List<StaffDetail> findAllStaffDetail();

    StaffDetail findById(int id);

    StaffDetail findByStaffId(String staffId);

    List<StaffDetail> findByStaffName(String staffName);

    StaffDetail insertStaffDetail(StaffDetail staffDetail);

    StaffDetail updateStaffDetail(String staffId, String staffFirstName, String staffLastName, String address, String phone, String avatar, Timestamp update_at, int id);
}
