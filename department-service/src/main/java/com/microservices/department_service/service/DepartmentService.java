package com.microservices.department_service.service;

import com.microservices.department_service.dto.DepartmentRequestDto;
import com.microservices.department_service.dto.DepartmentResponseDto;
import com.microservices.department_service.entity.DepartmentEntity;

import java.util.List;

public interface DepartmentService {
    DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto);

    DepartmentResponseDto getDepartmentById(Long id);

    List<DepartmentResponseDto> getAllDepartments();

    DepartmentResponseDto updateDepartment(
            Long id,
            DepartmentRequestDto requestDto);

    void deleteDepartment(Long id);

}
