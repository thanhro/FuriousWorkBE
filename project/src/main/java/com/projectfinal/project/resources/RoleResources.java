package com.projectfinal.project.resources;

import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.RoleUser;
import com.projectfinal.project.services.Role.IRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/role")
public class RoleResources {

    @Autowired
    private IRole roleService;

    @GetMapping()
    public ResponseEntity<List<RoleUser>> listAllRole(){
        List<RoleUser> listAllRole = roleService.findAllRoleUser();
        return ResponseObjectFactory.toResult(listAllRole, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<RoleUser> findRoleById(@RequestParam int id){
        RoleUser findRoleById = roleService.findById(id);
        return ResponseObjectFactory.toResult(findRoleById, HttpStatus.OK);
    }

    @GetMapping("/roleName")
    public ResponseEntity<List<RoleUser>> findRoleByName(@RequestParam String roleName){
        List<RoleUser> findRoleByName = roleService.findByRoleName(roleName);
        return ResponseObjectFactory.toResult(findRoleByName, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleUser> updateRole(@RequestParam String roleName, @RequestParam String description, @RequestParam Timestamp update_at, @RequestParam int id){
        roleService.updateRole(roleName,description,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleUser> insertRole(@RequestBody RoleUser roleUser){
        roleService.insertRole(roleUser);
        return ResponseObjectFactory.toResult("Insert Successfully", HttpStatus.OK);
    }
}
