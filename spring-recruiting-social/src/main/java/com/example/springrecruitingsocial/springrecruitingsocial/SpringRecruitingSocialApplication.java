package com.example.springrecruitingsocial.springrecruitingsocial;

import com.example.springrecruitingsocial.springrecruitingsocial.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SpringRecruitingSocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRecruitingSocialApplication.class, args);
    }

}
