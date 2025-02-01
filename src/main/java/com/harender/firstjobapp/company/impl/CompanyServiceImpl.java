package com.harender.firstjobapp.company.impl;

import com.harender.firstjobapp.company.Company;
import com.harender.firstjobapp.company.CompanyRepository;
import com.harender.firstjobapp.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies(){
        return companyRepository.findAll();
    }

    @Override
    public boolean updateCompany(Long id, Company updateCompany) {
        Optional<Company> companyOptional = companyRepository.findById(id);

        if(companyOptional.isPresent()){
            Company company = companyOptional.get();

            company.setName(updateCompany.getName());
            company.setDescription(updateCompany.getDescription());
            company.setJobs(updateCompany.getJobs());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public void createCompany(Company company){
        companyRepository.save(company);
    }

    @Override
    public boolean deleteCompanyById(Long id) {
            if(companyRepository.existsById(id)){
                companyRepository.deleteById(id);
                return true;
            }
         return false;
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }


}
