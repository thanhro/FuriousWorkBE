package com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2.user;

import java.util.Date;
import java.util.Map;

public abstract class OAuth2UserInfo {
    protected Map<String,Object> attributes;

    public OAuth2UserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public abstract String getId();

    public abstract String member_id();

    public abstract String getEmail();

    public abstract String getFirstName();

    public abstract String getLastName();

    public abstract Date getDob();

    public abstract String getAddress();

    public abstract String getPhone();

    public abstract String getAvatar();

}
