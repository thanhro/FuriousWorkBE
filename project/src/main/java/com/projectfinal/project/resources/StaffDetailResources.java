package com.projectfinal.project.resources;


import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.config.security.payload.StaffDetailForm;
import com.projectfinal.project.model.StaffDetail;
import com.projectfinal.project.repository.StaffDetailRepository;
import com.projectfinal.project.services.StaffDetail.IStaffDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/staff_detail")
public class StaffDetailResources {

    @Autowired
    private IStaffDetail staffDetailService;

    @Autowired
    private StaffDetailRepository staffDetailRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<StaffDetail>> listAllStaffDetail(){
        List<StaffDetail> listAllStaffDetail = staffDetailService.findAllStaffDetail();
        return ResponseObjectFactory.toResult(listAllStaffDetail, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STAFF')")
    @GetMapping("/id")
    public ResponseEntity<StaffDetail> findStaffDetailById(@RequestParam int id){
        StaffDetail findStaffDetailById = staffDetailService.findById(id);
        return ResponseObjectFactory.toResult(findStaffDetailById, HttpStatus.OK);
    } // FE send that person id, staff can see their account detail by this function

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STAFF')")
    @GetMapping("/staff_id")
    public ResponseEntity<StaffDetail> findStaffDetailByStaffId(@RequestParam String staff_id){
        StaffDetail findStaffDetailByStaffId = staffDetailService.findByStaffId(staff_id);
        return ResponseObjectFactory.toResult(findStaffDetailByStaffId, HttpStatus.OK);
    } // if role staff call function, company id need to be checked. Fix need


    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STAFF')")
    @GetMapping("/staff_name")
    public ResponseEntity<List<StaffDetail>> findStaffDetailByStaffName(@RequestParam String staff_name){
        List<StaffDetail> findStaffDetailByStaffName = staffDetailService.findByStaffName(staff_name);
        return ResponseObjectFactory.toResult(findStaffDetailByStaffName, HttpStatus.OK);
    } // if role staff call function, company id need to be checked. Fix need

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDetail> updateStaffDetail(@RequestBody StaffDetailForm staffDetailForm){
        Date date = new Date();
        String staff_id = staffDetailForm.getStaff_id();
        String staff_first_name = staffDetailForm.getStaff_first_name();
        String staff_last_name = staffDetailForm.getStaff_last_name();
        String address = staffDetailForm.getAddress();
        String phone = staffDetailForm.getPhone();
        String avatar = staffDetailForm.getAvatar();
        int id = staffDetailForm.getId();
        Timestamp update_at = new Timestamp(date.getTime());
        staffDetailService.updateStaffDetail(staff_id,staff_first_name,staff_last_name,address,phone,avatar,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_STAFF')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDetail> insertStaffDetail(@RequestBody StaffDetail staffDetail){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        staffDetail.setCreate_at(ts);
        staffDetail.setUpdate_at(ts);
        staffDetailRepository.save(staffDetail);
        return ResponseObjectFactory.toResult("Insert Successfully", HttpStatus.OK);
    }
}
