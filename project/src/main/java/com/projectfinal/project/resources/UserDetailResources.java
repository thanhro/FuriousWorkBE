package com.projectfinal.project.resources;

import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.UserDetail;
import com.projectfinal.project.repository.UserDetailRepository;
import com.projectfinal.project.services.UserDetail.IUserDetail;
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
@RequestMapping("/user_detail")
public class UserDetailResources {

    @Autowired
    private IUserDetail userDetailService;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserDetail>> listAllUserDetail(){
        List<UserDetail> listAllUserDetail = userDetailService.findAllUserDetail();
        return ResponseObjectFactory.toResult(listAllUserDetail, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER')")
    @GetMapping("/id")
    public ResponseEntity<UserDetail> findUserDetailById(@RequestParam int id){
        UserDetail findUserDetailById = userDetailService.findById(id);
        return ResponseObjectFactory.toResult(findUserDetailById, HttpStatus.OK);
    } // FE send that person id, user can see their account detail by this function

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user_id")
    public ResponseEntity<UserDetail> findUserDetailByUserId(@RequestParam String user_id){
        UserDetail findUserDetailByUserId = userDetailService.findByUserId(user_id);
        return ResponseObjectFactory.toResult(findUserDetailByUserId, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user_name")
    public ResponseEntity<List<UserDetail>> findUserDetailByUsername(@RequestParam String user_name){
        List<UserDetail> findUserDetailByUsername = userDetailService.findByUsername(user_name);
        return ResponseObjectFactory.toResult(findUserDetailByUsername, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetail> updateUserDetail(@RequestParam String user_id, @RequestParam String first_name, @RequestParam String last_name, @RequestParam Date dob,@RequestParam String address, @RequestParam String phone, @RequestParam String avatar, @RequestParam int id){
        Date date = new Date();
        Timestamp update_at = new Timestamp(date.getTime());
        userDetailService.updateUserDetail(user_id,first_name,last_name,dob,address,phone,avatar,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDetail> insertUserDetail(@RequestBody UserDetail userDetail){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        userDetail.setCreate_at(ts);
        userDetail.setUpdate_at(ts);
        userDetailRepository.save(userDetail);
        return ResponseObjectFactory.toResult("Insert Successfully", HttpStatus.OK);
    }
}
