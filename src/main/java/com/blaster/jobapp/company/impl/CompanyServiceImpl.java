package com.blaster.jobapp.company.impl;

import java.util.List;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.stereotype.Service;

import com.blaster.jobapp.company.Company;
import com.blaster.jobapp.company.CompanyRepository;
import com.blaster.jobapp.company.CompanyService;

@Service
public class CompanyServiceImpl implements CompanyService {
  private final CompanyRepository companyRepository;

  public CompanyServiceImpl(CompanyRepository companyRepository) {
    this.companyRepository = companyRepository;
  }


  @Override
  public List<Company> getAllCompanies() {
    List<Company> companies = companyRepository.findAll();
    for(Company company: companies) {
      company.getReviews();
    }
    return companies;
  }


  @Override
  public boolean updateCompany(Company company, Long id) {
    Optional<Company> companyOptional = companyRepository.findById(id);    
    if(companyOptional.isPresent()) {
      Company companyToUpdate = companyOptional.get();
      companyToUpdate.setName(company.getName());
      companyToUpdate.setDescription(company.getDescription());
      companyToUpdate.setJobs(company.getJobs());
      companyRepository.save(companyToUpdate);
      return true;
    }
    return false;
  }

  @Override
  public void createCompany(Company company) {
    companyRepository.save(company);
  }


  @Override
  public boolean deleteCompany(Long id) {
    try {
      companyRepository.deleteById(id);
      return true;
    } catch (Exception e) {
      return false;
    }
  }


  @Override
  public Company getCompanyById(Long id) {
    return companyRepository.findById(id).orElse(null);
  }
}
