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

    @GetMapping("/nameOfCompany")
    public ResponseEntity<List<CompanyDetail>> findCompanyDetailByNameOfCompany(@RequestParam String nameOfCompany){
        List<CompanyDetail> findCompanyDetailByNameOfCompany = companyDetailService.findByCompanyName(nameOfCompany);
        return ResponseObjectFactory.toResult(findCompanyDetailByNameOfCompany, HttpStatus.OK);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDetail> updateCompanyDetail(@RequestParam String nameOfCompany,@RequestParam String webOfCompany,@RequestParam String emailOfCompany,@RequestParam String phoneOfCompany,@RequestParam String addressOfCompany,@RequestParam String agencyAddress,@RequestParam String companyField,@RequestParam String description,@RequestParam int yearOfOperation,@RequestParam Timestamp update_at,@RequestParam int id){
        companyDetailService.updateCompanyDetail(nameOfCompany,webOfCompany,emailOfCompany,phoneOfCompany,addressOfCompany,agencyAddress,companyField,description,yearOfOperation,update_at,id);
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
