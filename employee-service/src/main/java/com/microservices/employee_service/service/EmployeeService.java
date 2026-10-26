package com.microservices.employee_service.service;

import com.microservices.employee_service.dto.EmployeeRequestDto;
import com.microservices.employee_service.dto.EmployeeResponseDto;

public interface EmployeeService {
    EmployeeResponseDto createEmployee(EmployeeRequestDto employee);
}
