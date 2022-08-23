package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import com.example.departmentservice.service.impl.DepartmentServiceImpl;
import com.example.departmentservice.utility.Routes;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.convert.DataSizeUnit;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DepartmentRepository repository;

    DepartmentDto departmentDto;

    Department department;

    @Autowired
    private ObjectMapper objectMapper;


    @Autowired
    DepartmentService service;

    @BeforeEach
    void setup(){
        department = new Department(
                1L,
                "Consulting",
                "Street Two",
                "2345"
        );
        departmentDto = new DepartmentDto(
                "Finance",
                "down town street",
                "01234"
        );
    }


    @Test
    @DisplayName("should create a department")
    void shouldCreateADepartment() throws Exception {

        this.mockMvc.perform(post(Routes.DEPARTMENTS_V1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(departmentDto.getName()))
                .andExpect(jsonPath("$.code").value(departmentDto.getCode()))
                .andExpect(jsonPath("$.address").value(departmentDto.getAddress()))
                .andExpect(status().isCreated())
                .andReturn();

    }

    @Test
    @DisplayName("should not create a department")
    void shouldNotCreateADepartment() throws Exception {

        departmentDto.setName("");
        departmentDto.setAddress("");
        departmentDto.setCode("");

        this.mockMvc.perform(post(Routes.DEPARTMENTS_V1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(departmentDto))
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andReturn();

    }


    @Test
    @DisplayName("should return the correct department by id")
    void shouldReturnTheCorrectDepartment() throws Exception {
        repository.save(department);

        this.mockMvc.perform(get(Routes.DEPARTMENTS_V1 + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.name").value(department.getName()))
                .andExpect(jsonPath("$.code").value(department.getCode()))
                .andExpect(jsonPath("$.address").value(department.getAddress()))
                .andExpect(status().isOk())
                .andReturn();
    }
    @Test
    @DisplayName("should not return a department by id")
    void shouldReturnNotFoundWhenDepartmentNotExists() throws Exception {
        this.mockMvc.perform(get(Routes.DEPARTMENTS_V1 + "/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("should fetch all departments")
    void shouldFetchAllDepartments() throws Exception {
        repository.save(department);

        this.mockMvc.perform(get(Routes.DEPARTMENTS_V1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(1)))
                .andReturn();
    }

    @Test
    @DisplayName("should not fetch all departments")
    void shouldNotFetchAllDepartments() throws Exception {

        this.mockMvc.perform(get(Routes.DEPARTMENTS_V1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$", hasSize(0)))
                .andReturn();
    }







}