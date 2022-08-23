package com.example.departmentservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    @NotEmpty
    private String name;
    @NotEmpty
    private String address;
    @NotEmpty
    private String code;

}
