package com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2.user;

import java.util.Date;
import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo{
    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public String getId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String member_id() {
        return (String) attributes.get("member_id");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getFirstName() {
        return (String) attributes.get("first_name");
    }

    @Override
    public String getLastName() {
        return (String) attributes.get("last_name");
    }

    @Override
    public Date getDob() {
        return (Date) attributes.get("date");
    }

    @Override
    public String getAddress() {
        return (String) attributes.get("address");
    }

    @Override
    public String getPhone() {
        return (String) attributes.get("phone");
    }

    @Override
    public String getAvatar() {
        return (String) attributes.get("avatar");
    }
}
