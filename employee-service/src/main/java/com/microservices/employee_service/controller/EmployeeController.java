package com.microservices.employee_service.controller;

import com.microservices.employee_service.dto.EmployeeRequestDto;
import com.microservices.employee_service.dto.EmployeeResponseDto;
import com.microservices.employee_service.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @PostMapping
    public EmployeeResponseDto createEmployee(@Valid @RequestBody EmployeeRequestDto request) {
        return employeeService.createEmployee(request);
    }
}
