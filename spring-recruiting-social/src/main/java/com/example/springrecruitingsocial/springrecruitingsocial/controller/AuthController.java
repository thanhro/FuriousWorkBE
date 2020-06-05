package com.example.springrecruitingsocial.springrecruitingsocial.controller;

import com.example.springrecruitingsocial.springrecruitingsocial.exception.BadRequestException;
import com.example.springrecruitingsocial.springrecruitingsocial.model.UserLogin;
import com.example.springrecruitingsocial.springrecruitingsocial.payload.ApiResponse;
import com.example.springrecruitingsocial.springrecruitingsocial.payload.AuthResponse;
import com.example.springrecruitingsocial.springrecruitingsocial.payload.LoginRequest;
import com.example.springrecruitingsocial.springrecruitingsocial.payload.SignUpCustomerRequest;
import com.example.springrecruitingsocial.springrecruitingsocial.repository.UserLoginRepository;
import com.example.springrecruitingsocial.springrecruitingsocial.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser (@Valid @RequestBody SignUpCustomerRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new BadRequestException("Username adready in use !");
        }

        UserLogin user = new UserLogin();
        user.setMember_id("");/*admin will set this attribute*/
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole_id(1);
        user.setStatus(1);
        user.setCreatedAt("");/*Set time create*/
        user.setUpdatedAt("");/*First time create account update time = create time*/

        UserLogin results = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/me").buildAndExpand(results.getId()).toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
    }


}
