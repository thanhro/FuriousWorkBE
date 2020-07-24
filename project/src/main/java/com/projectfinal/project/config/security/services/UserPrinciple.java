package com.projectfinal.project.config.security.services;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectfinal.project.model.Admin;
import com.projectfinal.project.model.StaffLogin;
import com.projectfinal.project.model.UserLogin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class UserPrinciple implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String id;

    private String email;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    public UserPrinciple(String id, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserPrinciple buildUser(UserLogin userLogin){
        List<GrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new UserPrinciple (userLogin.getId(),userLogin.getEmail(),userLogin.getPassword(),authorityList);
    }

    public static StaffPrinciple buildStaff(StaffLogin staffLogin){
        List<GrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority("ROLE_STAFF"));
        return new StaffPrinciple (staffLogin.getId(),staffLogin.getEmail(),staffLogin.getPassword(),authorityList);
    }

    public static AdminPrinciple buildAdmin(Admin admin){
        List<GrantedAuthority> authorityList = Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return new AdminPrinciple (admin.getId(),admin.getEmail(),admin.getPassword(),authorityList);
    }

    public String getId() {
        return id;
    }


    public String getEmail() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
