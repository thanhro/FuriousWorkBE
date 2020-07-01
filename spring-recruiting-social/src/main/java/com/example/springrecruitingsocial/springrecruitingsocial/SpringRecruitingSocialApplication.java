package com.example.springrecruitingsocial.springrecruitingsocial;

import com.example.springrecruitingsocial.springrecruitingsocial.config.AppProperties;
import com.example.springrecruitingsocial.springrecruitingsocial.controller.AuthController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringRecruitingSocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRecruitingSocialApplication.class, args);
    }

}
