package com.projectfinal.project.config.security.payload;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class CompanyDetailForm {
    @NotBlank
    private String name_of_company;

    @NotBlank
    private String web_of_company;

    @NotBlank
    private String email_of_company;

    @NotBlank
    private String phone_of_company;

    @NotBlank
    private String address_of_company;


    private String agency_address_company;

    @NotBlank
    private String company_field;


    private String description;

    @NotBlank
    private int year_of_operation;

    @NotBlank
    private int status;

    private int id;

    private String staff_login_id;

    public String getName_of_company() {
        return name_of_company;
    }

    public void setName_of_company(String name_of_company) {
        this.name_of_company = name_of_company;
    }

    public String getWeb_of_company() {
        return web_of_company;
    }

    public void setWeb_of_company(String web_of_company) {
        this.web_of_company = web_of_company;
    }

    public String getEmail_of_company() {
        return email_of_company;
    }

    public void setEmail_of_company(String email_of_company) {
        this.email_of_company = email_of_company;
    }

    public String getPhone_of_company() {
        return phone_of_company;
    }

    public void setPhone_of_company(String phone_of_company) {
        this.phone_of_company = phone_of_company;
    }

    public String getAddress_of_company() {
        return address_of_company;
    }

    public void setAddress_of_company(String address_of_company) {
        this.address_of_company = address_of_company;
    }

    public String getAgency_address_company() {
        return agency_address_company;
    }

    public void setAgency_address_company(String agency_address_company) {
        this.agency_address_company = agency_address_company;
    }

    public String getCompany_field() {
        return company_field;
    }

    public void setCompany_field(String company_field) {
        this.company_field = company_field;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getYear_of_operation() {
        return year_of_operation;
    }

    public void setYear_of_operation(int year_of_operation) {
        this.year_of_operation = year_of_operation;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getStaff_login_id() {
        return staff_login_id;
    }

    public void setStaff_login_id(String staff_login_id) {
        this.staff_login_id = staff_login_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
