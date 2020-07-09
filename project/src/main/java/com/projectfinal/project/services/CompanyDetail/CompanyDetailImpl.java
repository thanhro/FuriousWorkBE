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
    public List<CompanyDetail> findByCompanyName(String nameOfCompany) {
        List<CompanyDetail> findByCompanyName = companyDetailRepository.findByCompanyName(nameOfCompany);
        return findByCompanyName;
    }

    @Override
    public CompanyDetail insertCompanyDetail(CompanyDetail companyDetail) {
        return companyDetailRepository.insertCompanyDetail(companyDetail);
    }

    @Override
    public CompanyDetail updateCompanyDetail(String nameOfCompany, String webOfCompany, String emailOfCompany, String phoneOfCompany, String addressOfCompany, String agencyAddress, String companyField, String description, int yearOfOperation, Timestamp update_at, int id) {
        return companyDetailRepository.updateCompanyDetail(nameOfCompany,webOfCompany,emailOfCompany,phoneOfCompany,addressOfCompany,agencyAddress,companyField,description,yearOfOperation,update_at,id);
    }
}
