package com.projectfinal.project.config.security.services;

import com.projectfinal.project.model.Admin;
import com.projectfinal.project.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class AdminService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Admin Not Found with -> Email : " + email));
        return UserPrinciple.buildAdmin(admin);
    }
}
