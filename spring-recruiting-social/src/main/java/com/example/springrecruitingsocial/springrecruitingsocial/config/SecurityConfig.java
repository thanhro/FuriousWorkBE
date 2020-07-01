package com.example.springrecruitingsocial.springrecruitingsocial.config;

import com.example.springrecruitingsocial.springrecruitingsocial.security.CustomUserDetailService;
import com.example.springrecruitingsocial.springrecruitingsocial.security.RestAuthenticationEntryPoint;
import com.example.springrecruitingsocial.springrecruitingsocial.security.TokenAuthenticationFilter;
import com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2.CustomOAuth2UserService;
import com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2.OAuth2AuthenticationFailureHandle;
import com.example.springrecruitingsocial.springrecruitingsocial.security.oauth2.OAuth2AuthenticationSuccessHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private CustomOAuth2UserService customOAuth2UserService;

    @Autowired
    private OAuth2AuthenticationFailureHandle oAuth2AuthenticationFailureHandle;

    @Autowired
    private OAuth2AuthenticationSuccessHandle oAuth2AuthenticationSuccessHandle;

    @Autowired
    private HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

    @Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(){
        return new TokenAuthenticationFilter();
    }

    @Bean
    public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository(){
        return new HttpCookieOAuth2AuthorizationRequestRepository();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(customUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable().formLogin().disable().httpBasic().disable().exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and().authorizeRequests().antMatchers("/","/error","favicon.ico","/**/*.png","/**/*.gif","/**/*.svg","/**/*.jpg","/**/*.html","/**/*.css","**/*.js").permitAll()
                .antMatchers("/auth/**","/oauth2/**").permitAll()
                .anyRequest().authenticated()
                .and().oauth2Login().authorizationEndpoint().baseUri("/oauth2/authorize").authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository())
                .and().redirectionEndpoint().baseUri("/oauth2/callback")
                .and().userInfoEndpoint().userService(customOAuth2UserService)
                .and().successHandler(oAuth2AuthenticationSuccessHandle).failureHandler(oAuth2AuthenticationFailureHandle);

        http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
