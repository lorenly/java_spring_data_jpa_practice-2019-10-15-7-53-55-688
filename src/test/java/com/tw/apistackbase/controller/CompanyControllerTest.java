package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.core.CompanyProfile;
import com.tw.apistackbase.core.Employee;
import com.tw.apistackbase.repository.CompanyRepository;
import com.tw.apistackbase.service.CompanyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@ActiveProfiles(profiles = "test")
class CompanyControllerTest {
    @MockBean
    private CompanyService companyService;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    // getCompanyByNameLike
    @Test
    void should_return_company_with_name_Summit_when_Sum_entered_to_getCompanyByNameLike() throws Exception {
        //given
        Company company = new Company("Summit");
        when(companyService.findByNameContaining("S")).thenReturn(company);
        //when
        ResultActions result = mvc.perform(get("/companies?name=S"));
        //then
        result.andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.name").value("Summit"))
        ;
    }

//     /companies/all?size=0&pageSize=5 findAll
//    @Test
//    void should_return_one_item_when_Sum_entered_to_getCompanyByNameLike() throws Exception {
//        //given
//        List<Company> companies = new ArrayList<>();
//        Company company = new Company();
//        company.setName("Summit");
//        company.setId(1);
//        companies.add(company);
//        when(companyRepository.findByNameContaining("Sum")).thenReturn(company);
//        //when
//        ResultActions result = mvc.perform(get("/companies").param("name",company.getName()));
//        //then
//        result.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$", hasSize(1)))
//        ;
//    }

    @Test
    public void should_return_200_when_company_is_deleted() throws Exception {
        Company company = new Company("CompanyOne");
        when(companyService.findByNameContaining("CompanyOne")).thenReturn(company);
        ResultActions result = mvc.perform(delete("/companies/CompanyOne"));
        result.andExpect(status().isOk())
        ;
    }

    @Test
    void should_return_updated_company() throws Exception {
        Company company = new Company("CompanyOne");
        companyService.updateCompanyInfo("CompanyTwo", company);

        when(companyService.findByNameContaining("CompanyTwo")).thenReturn(company);

        ResultActions result = mvc.perform(patch("/companies/CompanyTwo").contentType("application/json").content(objectMapper.writeValueAsString(company)));
        result.andExpect(status().isOk())
        ;
    }

    @Test
    void should_return_company_information_when_company_added() throws Exception {
        //given
        Company company = new Company("CompanyOne");
        when(companyService.findByNameContaining("CompanyOne")).thenReturn(company);
        //when
        ResultActions result = mvc.perform(post("/companies").contentType("application/json").content(objectMapper.writeValueAsString(company)));
        //then
        result.andExpect(status().isCreated())
                .andDo(print())
        ;
    }
}
