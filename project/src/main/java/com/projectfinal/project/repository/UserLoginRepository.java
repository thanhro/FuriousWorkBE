package com.projectfinal.project.repository;

import com.projectfinal.project.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,String> {

    Optional<UserLogin> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT EXISTS( SELECT TRUE FROM user_login WHERE id = ?1)", nativeQuery = true)
    Boolean existsIdByRandom(String id);

    @Query(value = "SELECT * FROM user_login WHERE id = ?1", nativeQuery = true)
    UserLogin findById(int id);

    @Query(value = "SELECT * FROM user_login WHERE role_id = ?1",nativeQuery = true)
    List<UserLogin> findByRoleId(int roleId);

    @Query(value = "SELECT * FROM user_login WHERE status = ?1", nativeQuery = true)
    List<UserLogin> findByStatus(int status);

    @Query(value = "INSERT INTO user_login (email,password,role_id,status,create_at,update_at) values = ?1, ?2, ?3, ?4, ?5, ?6", nativeQuery = true)
    UserLogin insertUserLogin(UserLogin userLogin);

    @Query(value = "UPDATE user_login SET email = ?1, password = ?2, role_id = ?3, status = ?4, update_at = ?5 WHERE id = ?6", nativeQuery = true)
    UserLogin updateUserLogin(String email, String password, int role_id, int status, Timestamp update_at, int id);



}
