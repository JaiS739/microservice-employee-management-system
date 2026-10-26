package com.microservices.employee_service.client;

import com.microservices.employee_service.exception.DepartmentResourceNotFound;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
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

    public void validateDepartment(Long departmentId) {
        restClient.get()
                .uri("/api/departments/{id}", departmentId)
                .retrieve()
                .onStatus(status -> status.value() == 404,
                        (request, response) -> {
                    throw new DepartmentResourceNotFound( "Department not found with id: " + departmentId);
                        })
                .toBodilessEntity();
    }
}
