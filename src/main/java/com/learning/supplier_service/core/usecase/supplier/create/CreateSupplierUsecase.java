package com.learning.supplier_service.core.usecase.supplier.create;

import com.learning.supplier_service.core.service.supplier.create.CreateSupplierService;
import com.learning.supplier_service.core.service.supplier.create.CreateSupplierServiceRequest;
import com.learning.supplier_service.core.service.supplier.create.CreateSupplierServiceResponse;
import com.learning.supplier_service.core.usecase.Usecase;
import com.learning.supplier_service.exception.SupplierException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CreateSupplierUsecase implements Usecase<CreateSupplierUsecaseRequest, CreateSupplierUsecaseResponse> {
    private final CreateSupplierService createSupplierService;

    public CreateSupplierUsecase(CreateSupplierService createSupplierService) {
        this.createSupplierService = createSupplierService;
    }

    @Override
    public CreateSupplierUsecaseResponse execute(CreateSupplierUsecaseRequest request) {
        CreateSupplierServiceResponse createSupplierServiceResponse = createSupplierService.execute(buildSupplierServiceRequest(request));
        return Optional.of(createSupplierServiceResponse)
                .map(response -> buildSupplierUsecaseResponse(response))
                .orElseThrow(() -> new SupplierException("Failed to create supplier"));

    }

    private static CreateSupplierUsecaseResponse buildSupplierUsecaseResponse(CreateSupplierServiceResponse response) {
        return CreateSupplierUsecaseResponse.builder()
                .id(response.getId()).success(response.isSuccess()).build();
    }

    private static CreateSupplierServiceRequest buildSupplierServiceRequest(CreateSupplierUsecaseRequest request) {
        return CreateSupplierServiceRequest.builder()
                .legalName(request.getLegalName())
                .displayName(request.getDisplayName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .taxId(request.getTaxId())
                .country(request.getCountry())
                .commissionPct(request.getCommissionPct())
                .build();
    }
}
