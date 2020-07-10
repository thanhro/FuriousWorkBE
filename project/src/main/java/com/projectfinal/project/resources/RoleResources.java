package com.projectfinal.project.resources;

import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.config.security.payload.RoleUserInsertForm;
import com.projectfinal.project.model.RoleUser;
import com.projectfinal.project.services.Role.IRole;
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

    @GetMapping("/role_name")
    public ResponseEntity<List<RoleUser>> findRoleByName(@RequestParam String role_name){
        List<RoleUser> findRoleByName = roleService.findByRoleName(role_name);
        return ResponseObjectFactory.toResult(findRoleByName, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleUser> updateRole(@RequestParam String role_name, @RequestParam String description, @RequestParam Timestamp update_at, @RequestParam int id){
        roleService.updateRole(role_name,description,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RoleUser> insertRole(@RequestBody RoleUserInsertForm roleUserInsertForm){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        RoleUser roleUser = new RoleUser();
        roleUser.setRole_name(roleUserInsertForm.getRole_name());
        roleUser.setDescription(roleUserInsertForm.getDescription());
        roleUser.setCreate_at(ts);
        roleUser.setUpdate_at(ts);

        roleService.insertRole(roleUser);

        return ResponseObjectFactory.toResult("Insert Successfully", HttpStatus.OK);
    }
}
