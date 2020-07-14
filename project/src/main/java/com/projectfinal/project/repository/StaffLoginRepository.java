package com.projectfinal.project.repository;

import com.projectfinal.project.model.StaffLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

public interface StaffLoginRepository extends JpaRepository<StaffLogin,String> {
    Optional<StaffLogin> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT EXISTS( SELECT TRUE FROM staff_login WHERE id = ?1)", nativeQuery = true)
    Boolean existsIdByRandom(String id);

    @Query(value = "SELECT * FROM staff_login WHERE id = ?1", nativeQuery = true)
    StaffLogin findStaffLoginInfoById(String id);

    @Query(value = "SELECT * FROM staff_login WHERE role_id = ?1",nativeQuery = true)
    List<StaffLogin> findByRoleId(int role_id);

    @Query(value = "SELECT * FROM staff_login WHERE status = ?1", nativeQuery = true)
    List<StaffLogin> findByStatus(int status);

    @Query(value = "INSERT INTO staff_login (id, email,password,role_id,company_id,status,create_at,update_at) values (?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8)", nativeQuery = true)
    StaffLogin insertStaffLogin(StaffLogin staffLogin);

    @Query(value = "UPDATE staff_login SET email = ?1, password = ?2, role_id = ?3, company_id = ?4, status = ?5, update_at = ?6 WHERE id = ?7", nativeQuery = true)
    StaffLogin updateStaffLogin(String email, String password, int role_id, int company_id, int status, Timestamp update_at, int id);
}
