package com.projectfinal.project.resources;


import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.model.CompanyDetail;
import com.projectfinal.project.services.CompanyDetail.ICompanyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/companyDetail")
public class CompanyDetailResources {

    @Autowired
    private ICompanyDetail companyDetailService;

    @GetMapping()
    public ResponseEntity<List<CompanyDetail>> listAllCompanyDetail(){
        List<CompanyDetail> listAllCompanyDetail = companyDetailService.findAllCompanyDetail();
        return ResponseObjectFactory.toResult(listAllCompanyDetail, HttpStatus.OK);
    }

    @GetMapping("/id")
    public ResponseEntity<CompanyDetail> findCompanyDetailById(@RequestParam int id){
        CompanyDetail findCompanyDetailById = companyDetailService.findById(id);
        return ResponseObjectFactory.toResult(findCompanyDetailById, HttpStatus.OK);
    }

    @GetMapping("/name_of_company")
    public ResponseEntity<List<CompanyDetail>> findCompanyDetailByNameOfCompany(@RequestParam String name_of_company){
        List<CompanyDetail> findCompanyDetailByNameOfCompany = companyDetailService.findByCompanyName(name_of_company);
        return ResponseObjectFactory.toResult(findCompanyDetailByNameOfCompany, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDetail> updateCompanyDetail(@RequestParam String name_of_company,@RequestParam String web_of_company,@RequestParam String email_of_company,@RequestParam String phone_of_company,@RequestParam String address_of_company,@RequestParam String agency_address_company,@RequestParam String company_field,@RequestParam String description,@RequestParam int year_of_operation,@RequestParam Timestamp update_at,@RequestParam int id){
        companyDetailService.updateCompanyDetail(name_of_company,web_of_company,email_of_company,phone_of_company,address_of_company,agency_address_company,company_field,description,year_of_operation,update_at,id);
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDetail> insertCompanyDetail(@RequestBody CompanyDetail companyDetail){
        companyDetailService.insertCompanyDetail(companyDetail);
        return ResponseObjectFactory.toResult("Insert Successfully", HttpStatus.OK);
    }

//    @DeleteMapping("/id")
//    public ResponseEntity<CompanyDetail> deleteComment(@RequestParam int id){
//        companyDetailService.deleteCompanyInfo(id);
//        return ResponseObjectFactory.toResult("Delete Successfully", HttpStatus.OK);
//    }
}
