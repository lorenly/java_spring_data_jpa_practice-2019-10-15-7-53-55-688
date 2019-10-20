package com.tw.apistackbase.service;

import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.OK;

@Service
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public Iterable<Company> findAll(Integer page, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(page,pageSize, Sort.by("name").ascending()));
    }

    public Company findByNameContaining(String name) {
        return companyRepository.findByNameContaining(name);
    }

    public Company save(Company company) {
        return companyRepository.save(company);
    }

    public ResponseEntity deleteCompanyByName(String name) {
        Optional<Company> companyOptional = Optional.ofNullable(companyRepository.findOneByName(name));
        if (companyOptional.isPresent()) {
            companyRepository.delete(companyOptional.get());
            return new ResponseEntity<>(OK);
        } else {
            return null;
        }
    }

    public ResponseEntity updateCompanyInfo(String name, Company company) {
        Optional<Company> fetchCompany = Optional.ofNullable(companyRepository.findOneByName(name));
        if (fetchCompany.isPresent()) {
            fetchCompany.get().setName(company.getName());
            fetchCompany.get().setEmployees(company.getEmployees());
            fetchCompany.get().setProfile(company.getProfile());

            Company newCompany = companyRepository.save(fetchCompany.get());
            return new ResponseEntity<>(newCompany, HttpStatus.OK);
        }
        return null;
    }
}
