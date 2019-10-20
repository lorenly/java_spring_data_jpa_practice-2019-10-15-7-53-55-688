package com.tw.apistackbase.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.apistackbase.core.Company;
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

//    @Test
//    void should_return_one_page_with_2_pageSize_when_given_0_page_2_pageSize() throws Exception {
//        //given
//        Company company = new Company("Summit");
//        when(companyService.findByNameContaining("S")).thenReturn(company);
//        //when
//        ResultActions result = mvc.perform(get("companies/all?page=0&pageSize=2"));
//        //then
//        result.andExpect(status().isOk())
//                .andDo(print())
//                .andExpect(jsonPath("$").value("Summit"))
//        ;
//    }

    @Test
    public void should_return_200_ok_when_company_is_deleted() throws Exception {
        Company company = new Company("CompanyOne");
        when(companyService.findByNameContaining("CompanyOne")).thenReturn(company);
        ResultActions result = mvc.perform(delete("/companies/CompanyOne"));
        result.andExpect(status().isOk())
        ;
    }

    @Test
    public void should_return_404_not_found_when_delete_company_not_found() throws Exception {
        when(companyService.findByNameContaining("CompanyOne")).thenReturn(null);
        ResultActions resultActions = mvc.perform(delete("/companies/CompanyOne"));
        resultActions.andExpect(status().isNotFound());
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
