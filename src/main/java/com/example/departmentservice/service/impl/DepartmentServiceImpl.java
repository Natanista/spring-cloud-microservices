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
    public ResponseEntity<Department> save(DepartmentDto departmentDto) {
        log.info("save in DepartmentServiceImpl");
        Department department = new Department();
        BeanUtils.copyProperties(departmentDto, department);
        return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(department));
    }

    @Override
    public ResponseEntity<Object> findById(Long id) {
        log.info("findById inside DepartmentServiceImpl");
        Optional<Department> optionalDepartment = repository.findById(id);
        if(optionalDepartment.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        DepartmentDto departmentDto = new DepartmentDto();
        BeanUtils.copyProperties(optionalDepartment.get(), departmentDto);
        return ResponseEntity.status(HttpStatus.OK).body(departmentDto);
    }

    @Override
    public ResponseEntity<List<DepartmentDto>> findAll() {
        log.info("findAll inside DepartmentServiceImpl");
        List<Department> departments = repository.findAll();
        List<DepartmentDto> departmentDtos = new ArrayList<>();

        for(Department department: departments){
            var departmentDto = new DepartmentDto();
            BeanUtils.copyProperties(department, departmentDto);
            departmentDtos.add(departmentDto);
        }

        return ResponseEntity.status(HttpStatus.OK).body(departmentDtos);
    }
}
