package com.microservices.department_service.mapper;

import com.microservices.department_service.dto.DepartmentRequestDto;
import com.microservices.department_service.dto.DepartmentResponseDto;
import com.microservices.department_service.entity.DepartmentEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentEntity toEntity(DepartmentRequestDto dto);

    DepartmentResponseDto toResponseDto(DepartmentEntity entity);

    void updateEntity(
            DepartmentRequestDto dto,
            @MappingTarget DepartmentEntity entity
    );
}
