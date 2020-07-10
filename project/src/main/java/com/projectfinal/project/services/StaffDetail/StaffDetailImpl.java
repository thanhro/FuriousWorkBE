package com.projectfinal.project.services.StaffDetail;

import com.projectfinal.project.model.StaffDetail;
import com.projectfinal.project.repository.StaffDetailRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class StaffDetailImpl implements IStaffDetail{

    private StaffDetailRepository staffDetailRepository;

    @Override
    public List<StaffDetail> findAllStaffDetail() {
        List<StaffDetail> findAllStaffDetail = staffDetailRepository.findAll();
        return findAllStaffDetail;
    }

    @Override
    public StaffDetail findById(int id) {
        StaffDetail findById = staffDetailRepository.findById(id);
        return findById;
    }

    @Override
    public StaffDetail findByStaffId(String staff_id) {
        StaffDetail findByStaffId = staffDetailRepository.findByStaffId(staff_id);
        return findByStaffId;
    }

    @Override
    public List<StaffDetail> findByStaffName(String staff_name) {
        List<StaffDetail> findByStaffName = staffDetailRepository.findByStaffName(staff_name);
        return findByStaffName;
    }

    @Override
    public StaffDetail insertStaffDetail(StaffDetail staffDetail) {
        return staffDetailRepository.insertStaffDetail(staffDetail);
    }

    @Override
    public StaffDetail updateStaffDetail(String staff_id, String staff_first_name, String staff_last_name, String address, String phone, String avatar, Timestamp update_at, int id) {
        return staffDetailRepository.updateStaffDetail(staff_id,staff_first_name,staff_last_name,address,phone,avatar,update_at,id);
    }
}
