package com.harender.firstjobapp.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private CompanyService companyService;


    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("")
    public List<Company> getAllCompanies(){
        return companyService.getAllCompanies();
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCompanyById(@PathVariable Long id, @RequestBody Company updateCompany){
        boolean isUpdated = companyService.updateCompany(id,updateCompany);

        if(isUpdated){
            return new ResponseEntity<>("Company details Updated Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("")
    public ResponseEntity<String> addCompany(@RequestBody Company company){
        try{companyService.createCompany(company);
            return new ResponseEntity<>("Company details added successfully", HttpStatus.CREATED);
        }catch (Exception e)
    {

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long id){
        boolean isDeleted = companyService.deleteCompanyById(id);

        if(isDeleted){
            return new ResponseEntity<>("company successfully deleted", HttpStatus.OK);
        }
        return new ResponseEntity<>("Company not found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> getCompanyById(@PathVariable Long id){
        Company company = companyService.getCompanyById(id);
        if(company != null){
            return new ResponseEntity<>(company,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

// GET /companies
// PUT /companies/{id}
// POST /companies
// DELETE /companies/{id}
// GET /companies/{id}