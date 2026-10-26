package com.microservices.employee_service.service;

import com.microservices.employee_service.client.DepartmentClient;
import com.microservices.employee_service.dto.EmployeeRequestDto;
import com.microservices.employee_service.dto.EmployeeResponseDto;
import com.microservices.employee_service.entity.EmployeeEntity;
import com.microservices.employee_service.mapper.EmployeeMapper;
import com.microservices.employee_service.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final DepartmentClient departmentClient;
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    @Override
    public EmployeeResponseDto createEmployee(EmployeeRequestDto request) {
        departmentClient.validateDepartment(request.getDepartmentId());

        EmployeeEntity employeeEntity = employeeMapper.toEntity(request);

        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);

        return employeeMapper.toResponseDto(savedEntity);
    }

}
