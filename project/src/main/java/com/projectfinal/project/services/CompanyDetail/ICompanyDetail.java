package com.projectfinal.project.services.CompanyDetail;

import com.projectfinal.project.model.CompanyDetail;

import java.sql.Timestamp;
import java.util.List;

public interface ICompanyDetail {

    List<CompanyDetail> findAllCompanyDetail();

    CompanyDetail findById(int id);

    List<CompanyDetail> findByCompanyName(String nameOfCompany);

    CompanyDetail insertCompanyDetail(CompanyDetail companyDetail);

    CompanyDetail updateCompanyDetail(String nameOfCompany, String webOfCompany, String emailOfCompany, String phoneOfCompany, String addressOfCompany, String agencyAddress, String companyField, String description, int yearOfOperation, Timestamp update_at, int id);
}
