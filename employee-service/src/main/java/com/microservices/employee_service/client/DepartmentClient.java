package com.microservices.employee_service.client;

import com.microservices.employee_service.exception.DepartmentResourceNotFound;
import com.microservices.employee_service.exception.DepartmentServiceUnavailableException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DeadlockLoserDataAccessException;
import org.springframework.resilience.annotation.Retryable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class DepartmentClient {
    private final RestClient restClient;

    public DepartmentClient(RestClient.Builder builder,
                            @Value("${services.department.base-url}") String baseUrl) {
        this.restClient = builder
                .baseUrl(baseUrl)
                .build();
    }

    @Retryable(
            includes = DepartmentServiceUnavailableException.class,
            maxRetries = 2,
            delay = 500,
            multiplier = 2
    )
    @CircuitBreaker(name = "departmentService")
    public void validateDepartment(Long departmentId) {
        try {
            restClient.get()
                    .uri("/api/departments/{id}", departmentId)
                    .retrieve()
                    .onStatus(status -> status.value() == 404,
                            (request, response) -> {
                                throw new DepartmentResourceNotFound("Department not found with id: " + departmentId);
                            })
                    .toBodilessEntity();
        } catch (ResourceAccessException ex) {
            throw new DepartmentServiceUnavailableException("Unable to communicate with Department Service", ex);
        }
    }
}
