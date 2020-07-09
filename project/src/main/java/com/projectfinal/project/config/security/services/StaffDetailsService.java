package com.projectfinal.project.config.security.services;

import com.projectfinal.project.model.StaffLogin;
import com.projectfinal.project.repository.StaffLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class StaffDetailsService implements UserDetailsService {

    @Autowired
    StaffLoginRepository staffLoginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        StaffLogin staff = staffLoginRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Staff Account Not Found with -> Email : " + email));
        return UserPrinciple.buildStaff(staff);
    }
}
