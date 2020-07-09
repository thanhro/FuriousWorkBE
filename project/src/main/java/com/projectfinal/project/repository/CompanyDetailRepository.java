package com.projectfinal.project.repository;

import com.projectfinal.project.model.CompanyDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

public interface CompanyDetailRepository extends JpaRepository<CompanyDetail,Integer> {

    @Query(value = "SELECT * FROM company_detail WHERE id = ?1", nativeQuery = true)
    CompanyDetail findById(int id);

    @Query(value = "SELECT * FROM company_detail WHERE name_of_company LIKE %?1%", nativeQuery = true)
    List<CompanyDetail> findByCompanyName(String nameOfCompany);

    @Query(value = "INSERT INTO company_detail (name_of_company,web_of_company,email_of_company,phone_of_company,address_of_company,agency_address_company,company_field,description,year_of_operation,create_at,update_at) values = ?1, ?2, ?3, ?4, ?5, ?6, ?7, ?8, ?9, ?10, ?11", nativeQuery = true)
    CompanyDetail insertCompanyDetail(CompanyDetail companyDetail);

    @Query(value = "UPDATE company_detail SET name_of_company = ?1, web_of_company = ?2, email_of_company = ?3, phone_of_company = ?4, address_of_company = ?5, agency_address_company = ?6, company_field = ?7, description = ?8, year_of_operation = ?9, update_at = ?10 WHERE id = ?11", nativeQuery = true)
    CompanyDetail updateCompanyDetail(String nameOfCompany, String webOfCompany, String emailOfCompany, String phoneOfCompany, String addressOfCompany, String agencyAddress, String companyField, String description, int yearOfOperation, Timestamp update_at, int id);
}
