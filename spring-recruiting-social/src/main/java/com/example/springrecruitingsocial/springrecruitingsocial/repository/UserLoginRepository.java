package com.example.springrecruitingsocial.springrecruitingsocial.repository;

import com.example.springrecruitingsocial.springrecruitingsocial.model.UserLogin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLoginRepository extends JpaRepository<UserLogin,Long> {

    Optional<UserLogin> findByEmail(String email);

    Boolean existsByEmail(String email);

}
