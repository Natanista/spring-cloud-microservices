package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.repository.DepartmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DepartmentService {

    ResponseEntity<Department> save(DepartmentDto department);

    ResponseEntity<Object> findById(Long id);

    ResponseEntity<List<DepartmentDto>> findAll();
}
