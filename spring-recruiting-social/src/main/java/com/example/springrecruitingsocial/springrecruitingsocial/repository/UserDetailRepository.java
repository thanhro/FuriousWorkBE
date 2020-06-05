package com.example.springrecruitingsocial.springrecruitingsocial.repository;

import com.example.springrecruitingsocial.springrecruitingsocial.model.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail,Long> {

    Optional<UserDetail> findUserInfoByEmail(String email);
}
