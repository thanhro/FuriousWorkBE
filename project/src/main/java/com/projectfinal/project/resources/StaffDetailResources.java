package com.projectfinal.project.resources;


import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.StaffDetail;
import com.projectfinal.project.repository.StaffDetailRepository;
import com.projectfinal.project.services.StaffDetail.IStaffDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/staffDetail")
public class StaffDetailResources {

    @Autowired
    private IStaffDetail staffDetailService;

    @Autowired
    private StaffDetailRepository staffDetailRepository;

    @GetMapping()
    public ResponseEntity<List<StaffDetail>> listAllStaffDetail(){
        List<StaffDetail> listAllStaffDetail = staffDetailService.findAllStaffDetail();
        return ResponseObjectFactory.toResult(listAllStaffDetail, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<StaffDetail> findStaffDetailById(@RequestParam int id){
        StaffDetail findStaffDetailById = staffDetailService.findById(id);
        return ResponseObjectFactory.toResult(findStaffDetailById, HttpStatus.OK);
    }

    @GetMapping("/staff_id")
    public ResponseEntity<StaffDetail> findStaffDetailByStaffId(@RequestParam String staff_id){
        StaffDetail findStaffDetailByStaffId = staffDetailService.findByStaffId(staff_id);
        return ResponseObjectFactory.toResult(findStaffDetailByStaffId, HttpStatus.OK);
    }

    @GetMapping("/staff_name")
    public ResponseEntity<List<StaffDetail>> findStaffDetailByStaffName(@RequestParam String staff_name){
        List<StaffDetail> findStaffDetailByStaffName = staffDetailService.findByStaffName(staff_name);
        return ResponseObjectFactory.toResult(findStaffDetailByStaffName, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDetail> updateStaffDetail(@RequestParam String staff_id,@RequestParam String staff_first_name,@RequestParam String staff_last_name,@RequestParam String address,@RequestParam String phone,@RequestParam String avatar,@RequestParam int id){
        Date date = new Date();
        Timestamp update_at = new Timestamp(date.getTime());
        staffDetailService.updateStaffDetail(staff_id,staff_first_name,staff_last_name,address,phone,avatar,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

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
