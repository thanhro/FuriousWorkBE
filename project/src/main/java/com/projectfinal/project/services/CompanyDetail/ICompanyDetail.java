package com.projectfinal.project.services.CompanyDetail;

import com.projectfinal.project.model.CompanyDetail;

import java.sql.Timestamp;
import java.util.List;

public interface ICompanyDetail {

    List<CompanyDetail> findAllCompanyDetail();

    CompanyDetail findById(int id);

    List<CompanyDetail> findByCompanyName(String nameOfCompany);

    CompanyDetail insertCompanyDetail(CompanyDetail companyDetail);

    CompanyDetail updateCompanyDetail(String name_of_company, String web_of_company, String email_of_company, String phone_of_company, String address_of_company, String agency_address_company, String company_field, String description, int year_of_operation, Timestamp update_at, int id);

    CompanyDetail updateCompanyDetailByAdministrator (int status, int id);
}
