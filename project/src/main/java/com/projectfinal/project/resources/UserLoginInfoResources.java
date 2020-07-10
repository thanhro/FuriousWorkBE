package com.projectfinal.project.resources;

import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.UserLogin;
import com.projectfinal.project.services.UserLoginInfo.IUserLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/userLoginInfo")
public class UserLoginInfoResources {

    @Autowired
    private IUserLogin userLoginService;

    @GetMapping()
    public ResponseEntity<List<UserLogin>> listAllUserLoginInfo(){
        List<UserLogin> listAllUserLoginInfo = userLoginService.findAllUserLoginInfo();
        return ResponseObjectFactory.toResult(listAllUserLoginInfo, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<UserLogin> findUserLoginInfoById(@RequestParam String id){
        UserLogin findUserLoginInfoById = userLoginService.findById(id);
        return ResponseObjectFactory.toResult(findUserLoginInfoById, HttpStatus.OK);
    }

    @GetMapping("/roleId")
    public ResponseEntity<List<UserLogin>> findUserLoginInfoByRoleId(@RequestParam int role_id){
        List<UserLogin> findUserLoginInfoByRoleId = userLoginService.findByRoleId(role_id);
        return ResponseObjectFactory.toResult(findUserLoginInfoByRoleId, HttpStatus.OK);
    }

    @GetMapping("/status")
    public ResponseEntity<List<UserLogin>> findUserLoginInfoByStatus(@RequestParam int status){
        List<UserLogin> findUserLoginInfoByStatus = userLoginService.findByStatus(status);
        return ResponseObjectFactory.toResult(findUserLoginInfoByStatus, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserLogin> updateUserLoginInfo(@RequestParam String email, @RequestParam  String password, @RequestParam  int role_id, @RequestParam  int status, @RequestParam Timestamp update_at, @RequestParam  int id){
        userLoginService.updateUserLogin(email,password,role_id,status,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

}
