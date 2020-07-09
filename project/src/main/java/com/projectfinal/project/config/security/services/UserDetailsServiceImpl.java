package com.projectfinal.project.config.security.services;


import com.projectfinal.project.model.UserLogin;
import com.projectfinal.project.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserLoginRepository userLoginRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            UserLogin user = userLoginRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User Not Found with -> Email : " + email));
        return UserPrinciple.buildUser(user);
    }

}
