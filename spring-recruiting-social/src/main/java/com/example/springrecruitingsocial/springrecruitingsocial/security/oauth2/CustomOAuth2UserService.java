package com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2;

import com.example.springrecruitingsocial.springrecruitingsocial.exception.OAuth2AuthenticationProcessingException;
import com.example.springrecruitingsocial.springrecruitingsocial.model.AuthProvider;
import com.example.springrecruitingsocial.springrecruitingsocial.model.UserDetail;
import com.example.springrecruitingsocial.springrecruitingsocial.model.UserLogin;
import com.example.springrecruitingsocial.springrecruitingsocial.repository.UserDetailRepository;
import com.example.springrecruitingsocial.springrecruitingsocial.repository.UserLoginRepository;
import com.example.springrecruitingsocial.springrecruitingsocial.security.UserPrincipal;
import com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2.user.OAuth2UserInfo;
import com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2.user.OAuth2UserInfoFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    @Autowired
    private UserLoginRepository userRepository;

    @Autowired
    private UserDetailRepository userDetailRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (AuthenticationException ex) {
            throw ex;
        } catch (Exception ex) {
            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User){
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(oAuth2UserRequest.getClientRegistration().getRegistrationId(), oAuth2User.getAttributes());
        if(StringUtils.isEmpty(oAuth2UserInfo.getEmail())){
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }

        Optional<UserLogin> userOptional = userRepository.findByEmail(oAuth2UserInfo.getEmail());
        UserLogin user;
        UserDetail userDetail;
        // need to get member_id and get user information in table user detail
        if(userOptional.isPresent()){
            user = userOptional.get();
            if(!user.getProvider().equals(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()))){
                throw new OAuth2AuthenticationProcessingException("Look like you're sign up with " + user.getProvider() + " account. Please use your " + user.getProvider() + " account to login.");
            }
            user = updateExistingUser(user, oAuth2UserInfo);
            // need to updateExstingUser in user detail instead of updateExstingUser in user
            }
        else{
            user = registerNewUser(oAuth2UserRequest, oAuth2UserInfo);
            userDetail = registerNewUserInfo(oAuth2UserRequest, oAuth2UserInfo); // need to develop this
        }

        return UserPrincipal.create(user, oAuth2User.getAttributes());
    }

    private UserLogin registerNewUser(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo){
        UserLogin user = new UserLogin();

        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
        user.setProviderId(oAuth2UserInfo.getId());
        user.setEmail(oAuth2UserInfo.getEmail());

        return userRepository.save(user);
        //Need to save 2 repository // need to fix this one
    }

    private UserDetail registerNewUserInfo(OAuth2UserRequest oAuth2UserRequest, OAuth2UserInfo oAuth2UserInfo){
        UserDetail userDetail = new UserDetail();

//        user.setProvider(AuthProvider.valueOf(oAuth2UserRequest.getClientRegistration().getRegistrationId()));
//        user.setProviderId(oAuth2UserInfo.getId());
//        user.setEmail(oAuth2UserInfo.getEmail());
        userDetail.setFirst_name(oAuth2UserInfo.getFirstName());
        userDetail.setLast_name(oAuth2UserInfo.getLastName());
        userDetail.setDob(oAuth2UserInfo.getDob());
        userDetail.setAddress(oAuth2UserInfo.getAddress());
        userDetail.setPhone(oAuth2UserInfo.getPhone());
        userDetail.setAvatar(oAuth2UserInfo.getAvatar());
        return userDetailRepository.save(userDetail);
        //Need to save 2 repository // need to fix this one
    }

        private UserLogin updateExistingUser(UserLogin existingUser, OAuth2UserInfo oAuth2UserInfo){
            return userRepository.save(existingUser);
    }
}
