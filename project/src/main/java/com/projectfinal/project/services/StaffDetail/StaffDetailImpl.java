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
    public StaffDetail findByStaffId(String staffId) {
        StaffDetail findByStaffId = staffDetailRepository.findByStaffId(staffId);
        return findByStaffId;
    }

    @Override
    public List<StaffDetail> findByStaffName(String staffName) {
        List<StaffDetail> findByStaffName = staffDetailRepository.findByStaffName(staffName);
        return findByStaffName;
    }

    @Override
    public StaffDetail insertStaffDetail(StaffDetail staffDetail) {
        return staffDetailRepository.insertStaffDetail(staffDetail);
    }

    @Override
    public StaffDetail updateStaffDetail(String staffId, String staffFirstName, String staffLastName, String address, String phone, String avatar, Timestamp update_at, int id) {
        return staffDetailRepository.updateStaffDetail(staffId,staffFirstName,staffLastName,address,phone,avatar,update_at,id);
    }
}
