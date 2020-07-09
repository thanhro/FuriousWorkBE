package com.projectfinal.project.services.Role;

import com.projectfinal.project.model.RoleUser;
import com.projectfinal.project.repository.RoleUserRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RoleImpl implements IRole{

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
    public List<RoleUser> findByRoleName(String roleName) {
        List<RoleUser> findByRoleName = roleUserRepository.findByRoleName(roleName);
        return findByRoleName;
    }

    @Override
    public RoleUser insertRole(RoleUser roleUser) {
        return roleUserRepository.insertRole(roleUser);
    }

    @Override
    public RoleUser updateRole(String roleName, String description, Timestamp update_at, int id) {
        return roleUserRepository.updateRole(roleName,description,update_at,id);
    }
}
