package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.service.outbox.OutboxService;
import com.learning.supplier_service.core.service.supplier.SupplierService;
import com.learning.supplier_service.core.service.supplier.SupplierServiceRequest;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class CreateSupplierUseCase implements Usecase<CreateSupplierUsecaseRequest, CreateSupplierUsecaseResponse> {
    private final SupplierService supplierService;
    private final OutboxService outboxService;

    public CreateSupplierUseCase(SupplierService supplierService, OutboxService outboxService) {
        this.supplierService = supplierService;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public CreateSupplierUsecaseResponse execute(CreateSupplierUsecaseRequest request) {
        var supplier = supplierService.execute(buildServiceRequest(request));

        outboxService.append(supplier.getId(), "SUPPLIER_CREATED", new SupplierCreatedEvent(
                supplier.getId(),
                supplier.getDisplayName(),
                supplier.getEmail(),
                supplier.getOnboardingStatus().name()
        ));

        return new CreateSupplierUsecaseResponse(
                supplier.getId(),
                supplier.getLegalName(),
                supplier.getDisplayName(),
                supplier.getEmail(),
                supplier.getOnboardingStatus(),
                supplier.getCommissionPct(),
                supplier.getCreatedAt()
        );
    }

    private record SupplierCreatedEvent(
            java.util.UUID supplierId,
            String displayName,
            String email,
            String onboardingStatus
    ) {
    }

    private static SupplierServiceRequest buildServiceRequest(CreateSupplierUsecaseRequest request) {
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
