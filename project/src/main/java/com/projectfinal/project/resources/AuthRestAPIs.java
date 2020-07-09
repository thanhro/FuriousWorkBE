package com.projectfinal.project.resources;

import com.projectfinal.project.config.security.exception.BadRequestException;
import com.projectfinal.project.config.security.jwt.JwtProvider;
import com.projectfinal.project.config.security.payload.JwtResponse;
import com.projectfinal.project.config.security.payload.SignUpUserRequest;
import com.projectfinal.project.config.security.payload.StaffLoginForm;
import com.projectfinal.project.config.security.payload.UserLoginForm;
import com.projectfinal.project.config.security.supportTools.randomString;
import com.projectfinal.project.model.ResponseObjectFactory;
import com.projectfinal.project.model.UserDetail;
import com.projectfinal.project.model.UserLogin;
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
    UserLoginRepository userLoginRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/userLogin")
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

    @PostMapping("/staffLogin")
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

    @PostMapping("/userSignup")
    public ResponseEntity<?> registerUser (@Valid @RequestBody SignUpUserRequest signUpRequest){
        if(userLoginRepository.existsByEmail(signUpRequest.getEmail())){
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
        userLogin.setEmail(signUpRequest.getEmail());
        userLogin.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        userLogin.setRole_id(1);
        userLogin.setStatus(1);
        userLogin.setCreate_at(ts);/*Set time create*/
        userLogin.setUpdate_at(ts);/*First time create account update time = create time*/

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

        userLoginRepository.save(userLogin);
        userDetailRepository.save(userDetail);

        List response = new ArrayList();
        response.add("Sign up successfully");
        return ResponseObjectFactory.toResult(response, HttpStatus.OK);
    }
}
