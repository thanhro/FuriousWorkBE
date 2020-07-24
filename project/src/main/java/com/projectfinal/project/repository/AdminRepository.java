package com.projectfinal.project.repository;


import com.projectfinal.project.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,String> {

    Optional<Admin> findByEmail(String email);

    Boolean existsByEmail(String email);
}
