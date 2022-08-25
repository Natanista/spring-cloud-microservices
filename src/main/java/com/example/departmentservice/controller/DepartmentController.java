package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentDto;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.service.DepartmentService;
import com.example.departmentservice.utility.Routes;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(Routes.DEPARTMENTS_V1)
@AllArgsConstructor
@Slf4j
public class DepartmentController {

    private final DepartmentService service;

    @PostMapping
    public ResponseEntity<Department> saveDepartment(
            @RequestBody @Valid DepartmentDto department) {
        log.info("department saved in DepartmentController: %s", department);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(department));

    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(
            @PathVariable("id") Long id
    ) {
        log.info("findById in DepartmentController");
        Optional<Department> optionalDepartment = service.findById(id);
        if (optionalDepartment.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        DepartmentDto departmentDto = new DepartmentDto();
        BeanUtils.copyProperties(optionalDepartment.get(), departmentDto);
        return ResponseEntity.status(HttpStatus.OK).body(departmentDto);
    }

    @GetMapping
    public ResponseEntity<Object> findAll() {
        log.info("findAll in DepartmentController");
        List<DepartmentDto> departmentDtos = service.findAll();
        if (departmentDtos.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(departmentDtos);
    }


}
