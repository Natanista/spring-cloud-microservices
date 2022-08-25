package com.example.departmentservice.service.impl;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.DepartmentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository repository;

    @Override
    public Department save(DepartmentDto departmentDto) {
        log.info("save in DepartmentServiceImpl");
        Department department = new Department();
        BeanUtils.copyProperties(departmentDto, department);
        return repository.save(department);
    }

    @Override
    public Optional<Department> findById(Long id) {
        log.info("findById inside DepartmentServiceImpl");
        return repository.findById(id);
    }

    @Override
    public List<DepartmentDto> findAll() {
        log.info("findAll inside DepartmentServiceImpl");
        List<Department> departments = repository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for(Department department: departments){
            var departmentDto = new DepartmentDto();
            BeanUtils.copyProperties(department, departmentDto);
            departmentDtos.add(departmentDto);
        }

        return departmentDtos;
    }
}
