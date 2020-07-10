package com.projectfinal.project.services.Role;

import com.projectfinal.project.model.RoleUser;
import com.projectfinal.project.repository.RoleUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RoleImpl implements IRole{

    @Autowired
    private RoleUserRepository roleUserRepository;

    @Override
    public List<RoleUser> findAllRoleUser() {
        List<RoleUser> findAllRoleUser = roleUserRepository.findAll();
        return findAllRoleUser;
    }

    @Override
    public RoleUser findById(int id) {
        RoleUser findById = roleUserRepository.findById(id);
        return findById;
    }

    @Override
    public List<RoleUser> findByRoleName(String role_name) {
        List<RoleUser> findByRoleName = roleUserRepository.findByRoleName(role_name);
        return findByRoleName;
    }

    @Override
    public RoleUser insertRole(RoleUser roleUser) {
        return roleUserRepository.insertRole(roleUser);
    }

    @Override
    public RoleUser updateRole(String role_name, String description, Timestamp update_at, int id) {
        return roleUserRepository.updateRole(role_name,description,update_at,id);
    }
}
