package com.example.springrecruitingsocial.springrecruitingsocial.repository;

import com.example.springrecruitingsocial.springrecruitingsocial.model.RoleUser;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RoleUserRepository {

    @Query(value = "SELECT * FROM role_user WHERE id = ?1", nativeQuery = true)
    RoleUser findById(int id);

    @Query(value = "SELECT * FROM role_user WHERE role_name LIKE %?1%", nativeQuery = true)
    List<RoleUser> findByRoleName(String roleName);

    @Query (value = "INSERT INTO role (role_name,description,create_at,update_at) values = ?1, ?2, ?3, ?4", nativeQuery = true)
    RoleUser insertRole(RoleUser roleUser);

    @Query (value = "UPDATE role_user SET role_name = ?1, description = ?2, update_at = ?3 WHERE id = ?4", nativeQuery = true)
    RoleUser updateRole(String roleName, String description, Timestamp update_at, int id);
}
