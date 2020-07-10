package com.projectfinal.project.repository;

import com.projectfinal.project.model.RoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface RoleUserRepository extends JpaRepository<RoleUser,Integer> {

    @Query(value = "SELECT * FROM role_user WHERE id = ?1", nativeQuery = true)
    RoleUser findById(int id);

    @Query(value = "SELECT * FROM role_user WHERE role_name LIKE %?1%", nativeQuery = true)
    List<RoleUser> findByRoleName(String role_name);

    @Query(value = "INSERT INTO role_user (role_name,description,create_at,update_at) values (?1, ?2, ?3, ?4)", nativeQuery = true)
    RoleUser insertRole(RoleUser roleUser);

    @Query(value = "UPDATE role_user SET role_name = ?1, description = ?2, update_at = ?3 WHERE id = ?4", nativeQuery = true)
    RoleUser updateRole(String role_name, String description, Timestamp update_at, int id);
}
