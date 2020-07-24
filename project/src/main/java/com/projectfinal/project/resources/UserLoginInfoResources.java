package com.projectfinal.project.resources;

import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.UserLogin;
import com.projectfinal.project.services.UserLoginInfo.IUserLogin;
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
@RequestMapping("/user_login_info")
public class UserLoginInfoResources {

    @Autowired
    private IUserLogin userLoginService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping()
    public ResponseEntity<List<UserLogin>> listAllUserLoginInfo(){
        List<UserLogin> listAllUserLoginInfo = userLoginService.findAllUserLoginInfo();
        return ResponseObjectFactory.toResult(listAllUserLoginInfo, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/id")
    public ResponseEntity<UserLogin> findUserLoginInfoById(@RequestParam String id){
        UserLogin findUserLoginInfoById = userLoginService.findUserLoginInfoById(id);
        return ResponseObjectFactory.toResult(findUserLoginInfoById, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/role_id")
    public ResponseEntity<List<UserLogin>> findUserLoginInfoByRoleId(@RequestParam int role_id){
        List<UserLogin> findUserLoginInfoByRoleId = userLoginService.findByRoleId(role_id);
        return ResponseObjectFactory.toResult(findUserLoginInfoByRoleId, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/status")
    public ResponseEntity<List<UserLogin>> findUserLoginInfoByStatus(@RequestParam int status){
        List<UserLogin> findUserLoginInfoByStatus = userLoginService.findByStatus(status);
        return ResponseObjectFactory.toResult(findUserLoginInfoByStatus, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLogin> updateUserLoginInfo(@RequestParam String email, @RequestParam  String password, @RequestParam  int role_id, @RequestParam  int status, @RequestParam  int id){
        Date date = new Date();
        Timestamp update_at = new Timestamp(date.getTime());
        userLoginService.updateUserLogin(email,password,role_id,status,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    } //Fix this function to change password function.

}
