package com.microservices.department_service.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
public class ErrorResponseDto {
    Instant timestamp;
    int status;
    String error;
    String message;
    String path;
}
