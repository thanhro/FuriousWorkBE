package com.projectfinal.project.resources;

import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.StaffLogin;
import com.projectfinal.project.services.StaffLoginInfo.IStaffLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/staffLoginInfo")
public class StaffLoginInfoResources {

    @Autowired
    private IStaffLogin staffLoginService;

    @GetMapping()
    public ResponseEntity<List<StaffLogin>> listAllStaffLoginInfo(){
        List<StaffLogin> listAllStaffLoginInfo = staffLoginService.findAllStaffLoginInfo();
        return ResponseObjectFactory.toResult(listAllStaffLoginInfo, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<StaffLogin> findStaffLoginInfoById(@RequestParam String id){
        StaffLogin findStaffLoginInfoById = staffLoginService.findById(id);
        return ResponseObjectFactory.toResult(findStaffLoginInfoById, HttpStatus.OK);
    }

    @GetMapping("/role_id")
    public ResponseEntity<List<StaffLogin>> findStaffLoginInfoByRoleId(@RequestParam int role_id){
        List<StaffLogin> findStaffLoginInfoByRoleId = staffLoginService.findByRoleId(role_id);
        return ResponseObjectFactory.toResult(findStaffLoginInfoByRoleId, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<StaffLogin>> findStaffLoginInfoByStatus(@RequestParam int status){
        List<StaffLogin> findStaffLoginInfoByStatus = staffLoginService.findByStatus(status);
        return ResponseObjectFactory.toResult(findStaffLoginInfoByStatus, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<StaffLogin> updateStaffLoginInfo(@RequestParam String email,@RequestParam  String password,@RequestParam  int role_id,@RequestParam  int company_id,@RequestParam  int status,@RequestParam  Timestamp update_at,@RequestParam  int id){
        staffLoginService.updateStaffLogin(email,password,role_id,company_id,status,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

}
