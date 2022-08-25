package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.impl.DepartmentServiceImpl;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.core.AutoConfigureCache;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    @Mock
    private DepartmentRepository repository;
    @InjectMocks
    private DepartmentServiceImpl service;

    private DepartmentDto departmentDto;

    private Department department;

    @BeforeEach
    void setupService() {
        department = new Department(1L ,"Finance", "Street Two", "1234");
        departmentDto  = new DepartmentDto("Finance", "Street Two", "1234");
    }

    @Test
    void save() {
        service.save(departmentDto);
        
    }

    @Test
    void findById() {

    }


    @Test
    @DisplayName("should return all departments")
    public void shouldReturnAllDepartments() {
        when(repository.findAll()).thenReturn(Arrays.asList(department));
       List<DepartmentDto> result = service.findAll();

       assertEquals(department.getName(), result.get(0).getName());
       assertEquals(1, result.size());
        verify(repository, times(1)).findAll();
    }
}