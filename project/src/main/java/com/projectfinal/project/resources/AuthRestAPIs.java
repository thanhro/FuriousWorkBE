package com.projectfinal.project.resources;

import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.config.security.exception.BadRequestException;
import com.projectfinal.project.config.security.jwt.JwtProvider;
import com.projectfinal.project.config.security.payload.*;
import com.projectfinal.project.config.security.supportTools.randomString;
import com.projectfinal.project.model.*;
import com.projectfinal.project.repository.StaffDetailRepository;
import com.projectfinal.project.repository.StaffLoginRepository;
import com.projectfinal.project.repository.UserDetailRepository;
import com.projectfinal.project.repository.UserLoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestAPIs {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserLoginRepository userLoginRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    private StaffLoginRepository staffLoginRepository;

    @Autowired
    private StaffDetailRepository staffDetailRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @GetMapping("/user_sign_in")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody UserLoginForm userLoginForm){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginForm.getEmail(),
                        userLoginForm.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @GetMapping("/staff_sign_in")
    public ResponseEntity<?> authenticateStaff (@Valid @RequestBody StaffLoginForm staffLoginForm){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        staffLoginForm.getEmail(),
                        staffLoginForm.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateJwtToken(authentication);
        return ResponseEntity.ok(new JwtResponse(jwt));
    }

    @PostMapping("/user_sign_up")
    public ResponseEntity<?> registerUser (@Valid @RequestBody SignUpUserRequest signUpUserRequest){
        if(userLoginRepository.existsByEmail(signUpUserRequest.getEmail())){
            throw new BadRequestException("Username already in use !");
        }

        randomString random = new randomString();
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        String memberId = "";
        do {
            memberId = random.random();
        } while (userLoginRepository.existsIdByRandom(memberId) == true);
        UserLogin userLogin = new UserLogin();
        userLogin.setId(memberId);/*admin will set this attribute*/
        userLogin.setEmail(signUpUserRequest.getEmail());
        userLogin.setPassword(passwordEncoder.encode(signUpUserRequest.getPassword()));
        userLogin.setRole_id(1);
        userLogin.setStatus(1);
        userLogin.setCreate_at(ts);/*Set time create*/
        userLogin.setUpdate_at(ts);/*First time create account update time = create time*/

        UserDetail userDetail = new UserDetail();
        userDetail.setMember_id(memberId);
        userDetail.setFirst_name(signUpUserRequest.getFirst_name());
        userDetail.setLast_name(signUpUserRequest.getLast_name());
        userDetail.setDob(signUpUserRequest.getDob());
        userDetail.setAddress(signUpUserRequest.getAddress());
        userDetail.setPhone(signUpUserRequest.getPhone());
        userDetail.setAvatar(signUpUserRequest.getAvatar());
        userDetail.setCreate_at(ts);
        userDetail.setUpdate_at(ts);

        userLoginRepository.save(userLogin);
        userDetailRepository.save(userDetail);

        List response = new ArrayList();
        response.add("Sign up successfully");
        return ResponseObjectFactory.toResult(response, HttpStatus.OK);
    }

    @PostMapping("/staff_sign_up")
    public ResponseEntity<?> registerStaff (@Valid @RequestBody SignUpStaffRequest signUpStaffRequest){
        if(staffLoginRepository.existsByEmail(signUpStaffRequest.getEmail())){
            throw new BadRequestException("Staff account already in use !");
        }

        randomString random = new randomString();
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        String staffId = "";
        do {
            staffId = random.random();
        } while (staffLoginRepository.existsIdByRandom(staffId) == true);
        StaffLogin staffLogin = new StaffLogin();
        staffLogin.setId(staffId);/*admin will set this attribute*/
        staffLogin.setEmail(signUpStaffRequest.getEmail());
        staffLogin.setPassword(passwordEncoder.encode(signUpStaffRequest.getPassword()));
        staffLogin.setRole_id(2);
        staffLogin.setCompany_id(signUpStaffRequest.getCompany_id());
        staffLogin.setStatus(1);
        staffLogin.setCreate_at(ts);/*Set time create*/
        staffLogin.setUpdate_at(ts);/*First time create account update time = create time*/

        StaffDetail staffDetail = new StaffDetail();
        staffDetail.setStaff_id(staffId);
        staffDetail.setStaff_first_name(signUpStaffRequest.getStaff_first_name());
        staffDetail.setStaff_last_name(signUpStaffRequest.getStaff_last_name());
        staffDetail.setAddress(signUpStaffRequest.getAddress());
        staffDetail.setPhone(signUpStaffRequest.getPhone());
        staffDetail.setAvatar(signUpStaffRequest.getAvatar());
        staffDetail.setCreate_at(ts);
        staffDetail.setUpdate_at(ts);

        staffLoginRepository.save(staffLogin);
        staffDetailRepository.save(staffDetail);

        List response = new ArrayList();
        response.add("Sign up successfully");
        return ResponseObjectFactory.toResult(response, HttpStatus.OK);
    }
}
