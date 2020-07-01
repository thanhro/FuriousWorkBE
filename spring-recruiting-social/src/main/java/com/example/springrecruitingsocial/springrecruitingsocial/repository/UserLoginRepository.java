package com.example.springrecruitingsocial.springrecruitingsocial.repository;

import com.example.springrecruitingsocial.springrecruitingsocial.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,String> {

    Optional<UserLogin> findByEmail(String email);

    Boolean existsByEmail(String email);

    @Query(value = "SELECT EXISTS( SELECT TRUE FROM user_login WHERE id = ?1)", nativeQuery = true)
    Boolean existsIdByRandom(String id);

}
