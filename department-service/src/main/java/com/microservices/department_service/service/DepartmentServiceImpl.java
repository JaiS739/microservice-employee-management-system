package com.microservices.department_service.service;

import com.microservices.department_service.dto.DepartmentRequestDto;
import com.microservices.department_service.dto.DepartmentResponseDto;
import com.microservices.department_service.entity.DepartmentEntity;
import com.microservices.department_service.exception.DepartmentNotFoundException;
import com.microservices.department_service.mapper.DepartmentMapper;
import com.microservices.department_service.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public DepartmentResponseDto createDepartment(DepartmentRequestDto requestDto) {

        DepartmentEntity entity = departmentMapper.toEntity(requestDto);

        DepartmentEntity savedEntity = departmentRepository.save(entity);

        return departmentMapper.toResponseDto(savedEntity);

    }

    @Override
    public DepartmentResponseDto getDepartmentById(Long id) {
        DepartmentEntity entity = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException((id)));

        return departmentMapper.toResponseDto(entity);
    }

    @Override
    public List<DepartmentResponseDto> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponseDto)
                .toList();
    }

    @Override
    public DepartmentResponseDto updateDepartment(
            Long id,
            DepartmentRequestDto requestDto) {

        DepartmentEntity existingEntity = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        departmentMapper.updateEntity(requestDto, existingEntity);

        DepartmentEntity entity = departmentRepository.save(existingEntity);
        return departmentMapper.toResponseDto(entity);
    }

    @Override
    public void deleteDepartment(Long id) {

        DepartmentEntity entity = departmentRepository.findById(id)
                .orElseThrow(() -> new DepartmentNotFoundException(id));

        departmentRepository.delete(entity);
    }

}
