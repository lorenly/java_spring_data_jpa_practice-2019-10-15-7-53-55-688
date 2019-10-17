package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.core.Company;
import com.tw.apistackbase.repository.CompanyRepository;
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

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(CompanyController.class)
@ActiveProfiles(profiles = "test")
class CompanyControllerTest {
    @Autowired
    private CompanyController companyController;
    @MockBean
    private CompanyRepository companyRepository;
    @Autowired
    private MockMvc mvc;
    @Autowired
    private ObjectMapper objectMapper;

    // getCompanyByNameLike
//    @Test
//    void should_return_company_with_name_Summit_when_Sum_entered_to_getCompanyByNameLike() throws Exception {
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

    // /companies/all?size=0&pageSize=5 findAll
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
}
