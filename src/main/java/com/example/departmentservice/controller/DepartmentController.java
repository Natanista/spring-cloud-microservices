package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;
import com.example.departmentservice.utility.Routes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Routes.DEPARTMENTS_V1)
@AllArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentService service;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(
            @RequestBody DepartmentDto department){
        log.info("department saved in DepartmentController: %s", department);
        return service.save(department);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @PathVariable("id") Long id
    ){
        log.info("findById in DepartmentController");
        return service.findById(id);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDto>> findAll(){
        log.info("findAll in DepartmentController");
        return service.findAll();
    }


}
