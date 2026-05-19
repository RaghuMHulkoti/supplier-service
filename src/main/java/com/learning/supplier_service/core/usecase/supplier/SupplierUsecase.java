package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.service.supplier.SupplierService;
import com.learning.supplier_service.core.service.supplier.SupplierServiceRequest;
import com.learning.supplier_service.core.service.supplier.SupplierServiceResponse;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class SupplierUsecase implements Usecase<SupplierUsecaseRequest, SupplierUsecaseResponse> {
    private final SupplierService supplierService;

    public SupplierUsecase(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @Override
    public SupplierUsecaseResponse execute(SupplierUsecaseRequest request) {
        SupplierServiceResponse supplierServiceResponse = supplierService.execute(buildSupplierServiceRequest(request));
        return Optional.of(supplierServiceResponse)
                .map(response -> buildSupplierUsecaseResponse(response))
                .orElseThrow(() -> new RuntimeException("Failed to create supplier"));

    }

    private static SupplierUsecaseResponse buildSupplierUsecaseResponse(SupplierServiceResponse response) {
        return SupplierUsecaseResponse.builder()
                .id(response.getId()).success(response.isSuccess()).build();
    }

    private static SupplierServiceRequest buildSupplierServiceRequest(SupplierUsecaseRequest request) {
        return SupplierServiceRequest.builder()
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
