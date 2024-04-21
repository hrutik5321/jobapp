package com.blaster.jobapp.company;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {
  private CompanyService companyService;

  public CompanyController(CompanyService companyService) {
    this.companyService = companyService;
  } 

  @GetMapping
  public ResponseEntity<List<Company>> getAllCompanies() {
    return new ResponseEntity<>(companyService.getAllCompanies(), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<Company> getCompanyById(@PathVariable Long id) {
    Company company = companyService.getCompanyById(id);
    return company != null
        ? ResponseEntity.ok(company)
        : new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }

  @PutMapping("/{id}")
  public ResponseEntity<String> updateCompany(@RequestBody Company company,@PathVariable Long id) {
    boolean isUpdated =    companyService.updateCompany(company, id);
    
    return isUpdated
        ? ResponseEntity.ok("Company Updated Successfully")
        : ResponseEntity.ok("Company Not Found");
  }

  @PostMapping
  public ResponseEntity<String> createCompany(@RequestBody Company company) {
    companyService.createCompany(company);
    return ResponseEntity.ok("Company Added Successfully");
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<String> deleteCompany(@PathVariable Long id) {
    boolean isDeleted = companyService.deleteCompany(id);
    return isDeleted
        ?  ResponseEntity.ok("Company Deleted Successfully")
        :  new ResponseEntity<>("Company Not Found", HttpStatus.NOT_FOUND);
  }
}
