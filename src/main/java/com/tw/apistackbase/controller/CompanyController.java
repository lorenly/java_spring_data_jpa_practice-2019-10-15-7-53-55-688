package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.service.CompanyService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    public static final String NOT_FOUND = "Not Found";
    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/all" ,produces = {"application/json"})
    public Iterable<Company> listAllCompany(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        return companyService.findAll(page,pageSize);
    }

    @GetMapping(produces = {"application/json"})
    public Company getCompanyByNameLike(@RequestParam String name){
        return companyService.findByNameContaining(name);
    }

    @ResponseStatus(code = CREATED)
    @PostMapping(produces = {"application/json"})
    public Company addCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

    @ResponseStatus(OK)
    @DeleteMapping(value = "/{name}", produces = {"application/json"})
    public ResponseEntity deleteCompanyByName(@PathVariable String name) throws Exception{
        Company findCompany = companyService.findByNameContaining(name);
        if(findCompany != null)
            return companyService.deleteCompanyByName(name);
        throw new NotFoundException(NOT_FOUND);
    }
    @PatchMapping(value = "/{name}",produces = {"application/json"})
    public ResponseEntity updateCompanyInfo(@PathVariable("name") String name, @RequestBody Company company) throws Exception{
        ResponseEntity updateCompany = companyService.updateCompanyInfo(name, company);
        Company findCompany = companyService.findByNameContaining(name);
        if(findCompany != null)
            return updateCompany;
        throw new NotFoundException(NOT_FOUND);
    }
}
