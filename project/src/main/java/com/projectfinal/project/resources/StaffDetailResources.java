package com.projectfinal.project.resources;


import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.StaffDetail;
import com.projectfinal.project.services.StaffDetail.IStaffDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/staffDetail")
public class StaffDetailResources {

    @Autowired
    private IStaffDetail staffDetailService;

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

    @GetMapping("/staffId")
    public ResponseEntity<StaffDetail> findStaffDetailByStaffId(@RequestParam String staffId){
        StaffDetail findStaffDetailByStaffId = staffDetailService.findByStaffId(staffId);
        return ResponseObjectFactory.toResult(findStaffDetailByStaffId, HttpStatus.OK);
    }

    @GetMapping("/staffName")
    public ResponseEntity<List<StaffDetail>> findStaffDetailByStaffName(@RequestParam String staffName){
        List<StaffDetail> findStaffDetailByStaffName = staffDetailService.findByStaffName(staffName);
        return ResponseObjectFactory.toResult(findStaffDetailByStaffName, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDetail> updateStaffDetail(@RequestParam String staffId,@RequestParam String staffFirstName,@RequestParam String staffLastName,@RequestParam String address,@RequestParam String phone,@RequestParam String avatar,@RequestParam Timestamp update_at,@RequestParam int id){
        staffDetailService.updateStaffDetail(staffId,staffFirstName,staffLastName,address,phone,avatar,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffDetail> insertStaffDetail(@RequestBody StaffDetail staffDetail){
        staffDetailService.insertStaffDetail(staffDetail);
        return ResponseObjectFactory.toResult("Insert Successfully", HttpStatus.OK);
    }
}
