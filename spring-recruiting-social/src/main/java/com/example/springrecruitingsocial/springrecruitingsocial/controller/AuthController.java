package com.example.springrecruitingsocial.springrecruitingsocial.controller;

import com.example.springrecruitingsocial.springrecruitingsocial.exception.BadRequestException;
import com.example.springrecruitingsocial.springrecruitingsocial.model.ResponseObjectFactory;
import com.example.springrecruitingsocial.springrecruitingsocial.model.UserDetail;
import com.example.springrecruitingsocial.springrecruitingsocial.model.UserLogin;
import com.example.springrecruitingsocial.springrecruitingsocial.payload.AuthResponse;
import com.example.springrecruitingsocial.springrecruitingsocial.payload.LoginRequest;
import com.example.springrecruitingsocial.springrecruitingsocial.payload.SignUpCustomerRequest;
import com.example.springrecruitingsocial.springrecruitingsocial.repository.UserDetailRepository;
import com.example.springrecruitingsocial.springrecruitingsocial.repository.UserLoginRepository;
import com.example.springrecruitingsocial.springrecruitingsocial.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.springrecruitingsocial.springrecruitingsocial.supportTools.randomString;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

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
        List response = new ArrayList();
        response.add("Sign in successfully");
        response.add(new AuthResponse(token));
        return ResponseObjectFactory.toResult(response, HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser (@Valid @RequestBody SignUpCustomerRequest signUpRequest){
        if(userRepository.existsByEmail(signUpRequest.getEmail())){
            throw new BadRequestException("Username already in use !");
        }

        randomString random = new randomString();
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        String memberId = "";
        do {
            memberId = random.random();
        } while (userRepository.existsIdByRandom(memberId) == true);
        UserLogin user = new UserLogin();
        user.setId(memberId);/*admin will set this attribute*/
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setRole_id(1);
        user.setStatus(1);
        user.setCreate_at(ts);/*Set time create*/
        user.setUpdate_at(ts);/*First time create account update time = create time*/

        UserDetail userDetail = new UserDetail();
        userDetail.setMember_id(memberId);
        userDetail.setFirst_name(signUpRequest.getFirst_name());
        userDetail.setLast_name(signUpRequest.getLast_name());
        userDetail.setDob(signUpRequest.getDob());
        userDetail.setAddress(signUpRequest.getAddress());
        userDetail.setPhone(signUpRequest.getPhone());
        userDetail.setAvatar(signUpRequest.getAvatar());
        userDetail.setCreate_at(ts);
        userDetail.setUpdate_at(ts);

        userRepository.save(user);
        userDetailRepository.save(userDetail);

        List response = new ArrayList();
        response.add("Sign up successfully");
        return ResponseObjectFactory.toResult(response, HttpStatus.OK);
    }


}
