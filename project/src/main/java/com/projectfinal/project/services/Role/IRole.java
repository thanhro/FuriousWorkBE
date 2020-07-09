package com.projectfinal.project.services.Role;


import com.projectfinal.project.model.RoleUser;

import java.sql.Timestamp;
import java.util.List;

public interface IRole {

    List<RoleUser> findAllRoleUser();

    RoleUser findById(int id);

    List<RoleUser> findByRoleName(String roleName);

    RoleUser insertRole(RoleUser roleUser);

    RoleUser updateRole(String roleName, String description, Timestamp update_at, int id);
}
