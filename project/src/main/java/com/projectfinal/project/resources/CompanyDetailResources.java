package com.projectfinal.project.resources;


import com.projectfinal.project.config.responseOb.ResponseObjectFactory;
import com.projectfinal.project.config.security.exception.BadRequestException;
import com.projectfinal.project.config.security.payload.CompanyDetailForm;
import com.projectfinal.project.model.CompanyDetail;
import com.projectfinal.project.repository.CompanyDetailRepository;
import com.projectfinal.project.services.CompanyDetail.ICompanyDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/company_detail")
public class CompanyDetailResources {

    @Autowired
    private ICompanyDetail companyDetailService;

    @Autowired
    private CompanyDetailRepository companyDetailRepository;

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STAFF') OR hasRole('ROLE_USER')")
    @GetMapping()
    public ResponseEntity<List<CompanyDetail>> listAllCompanyDetail(){
        List<CompanyDetail> listAllCompanyDetail = companyDetailService.findAllCompanyDetail();
        return ResponseObjectFactory.toResult(listAllCompanyDetail, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STAFF') OR hasRole('ROLE_USER')")
    @GetMapping("/id")
    public ResponseEntity<CompanyDetail> findCompanyDetailById(@RequestParam int id){
        CompanyDetail findCompanyDetailById = companyDetailService.findById(id);
        return ResponseObjectFactory.toResult(findCompanyDetailById, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STAFF') OR hasRole('ROLE_USER')")
    @GetMapping("/name_of_company")
    public ResponseEntity<List<CompanyDetail>> findCompanyDetailByNameOfCompany(@RequestParam String name_of_company){
        List<CompanyDetail> findCompanyDetailByNameOfCompany = companyDetailService.findByCompanyName(name_of_company);
        return ResponseObjectFactory.toResult(findCompanyDetailByNameOfCompany, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_STAFF') OR hasRole('ROLE_ADMIN')")
    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDetail> updateCompanyDetail(HttpServletRequest request, @RequestBody CompanyDetailForm companyDetailForm){
        if(companyDetailRepository.existsCompanyDetailByName_of_company(companyDetailForm.getName_of_company())){
            throw new BadRequestException("This company has already registered !");
        }
        Date date = new Date();
        String name_of_company = companyDetailForm.getName_of_company();
        String web_of_company = companyDetailForm.getWeb_of_company();
        String email_of_company = companyDetailForm.getEmail_of_company();
        String phone_of_company = companyDetailForm.getPhone_of_company();
        String address_of_company = companyDetailForm.getAddress_of_company();
        String agency_address_company = companyDetailForm.getAgency_address_company();
        String company_field = companyDetailForm.getCompany_field();
        String description = companyDetailForm.getDescription();
        int year_of_operation = companyDetailForm.getYear_of_operation();
        Timestamp update_at = new Timestamp(date.getTime());
        int id = companyDetailForm.getId();
        if (request.isUserInRole("ROLE_STAFF")) {
            int staff_login_id = companyDetailRepository.getCompanyId(companyDetailForm.getStaff_login_id());
            companyDetailService.updateCompanyDetail(name_of_company, web_of_company, email_of_company, phone_of_company, address_of_company, agency_address_company, company_field, description, year_of_operation, update_at, staff_login_id);
        }
        int status = companyDetailForm.getStatus();
        if (request.isUserInRole("ROLE_ADMIN")){
            companyDetailService.updateCompanyDetailByAdministrator(status,id);
        }
        return ResponseObjectFactory.toResult("Update Successfully", HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_STAFF')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CompanyDetail> insertCompanyDetail(@RequestBody CompanyDetail companyDetail){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        companyDetail.setCreate_at(ts);
        companyDetail.setUpdate_at(ts);
        companyDetail.setStatus(0);
        companyDetailRepository.save(companyDetail);
        return ResponseObjectFactory.toResult("Insert Successfully", HttpStatus.OK);
    }

//    @DeleteMapping("/id")
//    public ResponseEntity<CompanyDetail> deleteComment(@RequestParam int id){
//        companyDetailService.deleteCompanyInfo(id);
//        return ResponseObjectFactory.toResult("Delete Successfully", HttpStatus.OK);
//    }
}
