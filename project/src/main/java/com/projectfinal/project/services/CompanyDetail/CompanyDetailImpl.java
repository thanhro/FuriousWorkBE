package com.projectfinal.project.services.CompanyDetail;

import com.projectfinal.project.model.CompanyDetail;
import com.projectfinal.project.repository.CompanyDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class CompanyDetailImpl implements ICompanyDetail {

    @Autowired
    private CompanyDetailRepository companyDetailRepository;

    @Override
    public List<CompanyDetail> findAllCompanyDetail() {
        List<CompanyDetail> findAllCompanyDetail = companyDetailRepository.findAll();
        return findAllCompanyDetail;
    }

    @Override
    public CompanyDetail findById(int id) {
        CompanyDetail findById = companyDetailRepository.findById(id);
        return findById;
    }

    @Override
    public List<CompanyDetail> findByCompanyName(String name_of_company) {
        List<CompanyDetail> findByCompanyName = companyDetailRepository.findByCompanyName(name_of_company);
        return findByCompanyName;
    }

    @Override
    public CompanyDetail insertCompanyDetail(CompanyDetail companyDetail) {
        return companyDetailRepository.insertCompanyDetail(companyDetail);
    }

    @Override
    public CompanyDetail updateCompanyDetail(String name_of_company, String web_of_company, String email_of_company, String phone_of_company, String address_of_company, String agency_address_company, String company_field, String description, int year_of_operation, Timestamp update_at, int id) {
        return companyDetailRepository.updateCompanyDetail(name_of_company,web_of_company,email_of_company,phone_of_company,address_of_company,agency_address_company,company_field,description,year_of_operation,update_at,id);
    }
}
