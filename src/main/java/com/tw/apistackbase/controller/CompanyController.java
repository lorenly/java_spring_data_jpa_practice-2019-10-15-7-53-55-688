package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import com.tw.apistackbase.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyService companyService;

    @GetMapping(value = "/all" ,produces = {"application/json"})
    public Iterable<Company> listAllCompany(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer pageSize) {
        return companyService.getAll(page,pageSize);
    }

//    @GetMapping(value = "/{name}", produces = {"application/json"})
//    public Company getCompanyByName(@PathVariable String name){
//        return companyRepository.findOneByName(name);
//    }
    @GetMapping(produces = {"application/json"})
    public Company getCompanyByNameLike(@RequestParam String name){
        return companyService.findByNameContaining(name);
    }

    @ResponseStatus(code = CREATED)
    @PostMapping(produces = {"application/json"})
    public Company addCompany(@RequestBody Company company) {
        return companyService.save(company);
    }

    @DeleteMapping(value = "/{name}", produces = {"application/json"})
    public ResponseEntity deleteCompanyByName(@PathVariable String name){
        return companyService.deleteCompanyByName(name);
    }
    @PatchMapping(value = "/{name}",produces = {"application/json"})
    public ResponseEntity updateCompanyInfo(@PathVariable("name") String name, @RequestBody Company company) {
        return companyService.updateCompanyInfo(name, company);
    }
}
