package com.microservices.employee_service.exception;

public class DepartmentResourceNotFound extends RuntimeException {
    public DepartmentResourceNotFound(String message) {
        super(message);
    }
}
