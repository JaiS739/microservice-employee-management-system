package com.microservices.employee_service.mapper;

import com.microservices.employee_service.dto.EmployeeRequestDto;
import com.microservices.employee_service.dto.EmployeeResponseDto;
import com.microservices.employee_service.entity.EmployeeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeEntity toEntity(EmployeeRequestDto requestDto);

    EmployeeResponseDto toResponseDto(EmployeeEntity entity);

    void updateEntity(EmployeeRequestDto dto, @MappingTarget EmployeeEntity entity);
}
