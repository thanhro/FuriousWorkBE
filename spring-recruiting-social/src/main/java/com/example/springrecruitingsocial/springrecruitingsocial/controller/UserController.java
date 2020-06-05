package com.example.springrecruitingsocial.springrecruitingsocial.controller;

import com.example.springrecruitingsocial.springrecruitingsocial.exception.ResourceNotFoundException;
import com.example.springrecruitingsocial.springrecruitingsocial.model.UserLogin;
import com.example.springrecruitingsocial.springrecruitingsocial.repository.UserLoginRepository;
import com.example.springrecruitingsocial.springrecruitingsocial.security.CurrentUser;
import com.example.springrecruitingsocial.springrecruitingsocial.security.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserLoginRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('User')")
    public UserLogin getCurrentUser(@CurrentUser UserPrincipal userPrincipal){
        return userRepository.findById(userPrincipal.getId()).orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
