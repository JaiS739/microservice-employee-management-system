package com.microservices.employee_service.exception;

public class DepartmentServiceUnavailableException extends RuntimeException {
    public DepartmentServiceUnavailableException(
            String message, Throwable cause) {
        super(message, cause);
    }
}
