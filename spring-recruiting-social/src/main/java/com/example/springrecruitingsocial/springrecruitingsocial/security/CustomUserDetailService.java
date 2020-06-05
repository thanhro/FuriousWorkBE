package com.example.springrecruitingsocial.springrecruitingsocial.security;

import com.example.springrecruitingsocial.springrecruitingsocial.exception.ResourceNotFoundException;
import com.example.springrecruitingsocial.springrecruitingsocial.model.UserLogin;
import com.example.springrecruitingsocial.springrecruitingsocial.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    UserLoginRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserLogin user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id){
        UserLogin user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User","id",id));

        return UserPrincipal.create(user);
    }
}
