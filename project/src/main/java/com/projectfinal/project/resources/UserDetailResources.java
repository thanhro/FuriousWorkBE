package com.projectfinal.project.resources;

import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.UserDetail;
import com.projectfinal.project.services.UserDetail.IUserDetail;
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
@RequestMapping("/userDetail")
public class UserDetailResources {

    @Autowired
    private IUserDetail userDetailService;

    @GetMapping()
    public ResponseEntity<List<UserDetail>> listAllUserDetail(){
        List<UserDetail> listAllUserDetail = userDetailService.findAllUserDetail();
        return ResponseObjectFactory.toResult(listAllUserDetail, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<UserDetail> findUserDetailById(@RequestParam int id){
        UserDetail findUserDetailById = userDetailService.findById(id);
        return ResponseObjectFactory.toResult(findUserDetailById, HttpStatus.OK);
    }

    @GetMapping("/userId")
    public ResponseEntity<UserDetail> findUserDetailByUserId(@RequestParam String userId){
        UserDetail findUserDetailByUserId = userDetailService.findByUserId(userId);
        return ResponseObjectFactory.toResult(findUserDetailByUserId, HttpStatus.OK);
    }

    @GetMapping("/username")
    public ResponseEntity<List<UserDetail>> findUserDetailByUsername(@RequestParam String username){
        List<UserDetail> findUserDetailByUsername = userDetailService.findByUsername(username);
        return ResponseObjectFactory.toResult(findUserDetailByUsername, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetail> updateUserDetail(@RequestParam String userId, @RequestParam String firstName, @RequestParam String lastName, @RequestParam Date dob,@RequestParam String address, @RequestParam String phone, @RequestParam String avatar, @RequestParam Timestamp update_at, @RequestParam int id){
        userDetailService.updateUserDetail(userId,firstName,lastName,dob,address,phone,avatar,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetail> insertUserDetail(@RequestBody UserDetail userDetail){
        userDetailService.insertUserDetail(userDetail);
        return ResponseObjectFactory.toResult("Insert Successfully", HttpStatus.OK);
    }
}
