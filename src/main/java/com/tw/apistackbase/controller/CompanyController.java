package com.tw.apistackbase.controller;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    CompanyRepository companyRepository;
    @GetMapping(produces = {"application/json"})
    public Iterable<Company> list() {
        return companyRepository.findAll();
    }

    @GetMapping(value = "/{name}", produces = {"application/json"})
    public Company getCompanyByName(@PathVariable String name){
        return companyRepository.findOneByName(name);
    }

    @ResponseStatus(code = CREATED)
    @PostMapping(produces = {"application/json"})
    public Company add(@RequestBody Company company) {
        return companyRepository.save(company);
    }

    @DeleteMapping(value = "/{name}", produces = {"application/json"})
    public ResponseEntity deleteCompanyByName(@PathVariable String name){
        Optional<Company> companyOptional = Optional.ofNullable(companyRepository.findOneByName(name));
        if (companyOptional.isPresent()) {
            companyRepository.delete(companyOptional.get());
            return new ResponseEntity<>(OK);
        } else {
            return new ResponseEntity<>(NOT_FOUND);
        }
    }
    @PatchMapping(value = "/{name}",produces = {"application/json"})
    public ResponseEntity update(@PathVariable("name") String name, @RequestBody Company company) {
        Optional<Company> fetchCompany = Optional.ofNullable(companyRepository.findOneByName(name));
        if (fetchCompany.isPresent()) {
            fetchCompany.get().setName(company.getName());
            Company newCompany = companyRepository.save(company);
            return new ResponseEntity<>(newCompany, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
