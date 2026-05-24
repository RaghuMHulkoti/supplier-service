package com.learning.supplier_service.core.usecase.supplier;

import com.learning.supplier_service.core.service.outbox.OutboxService;
import com.learning.supplier_service.core.service.supplier.create.CreateSupplierService;
import com.learning.supplier_service.core.usecase.Usecase;
import com.learning.supplier_service.gateway.SupplierKycGateway;
import com.learning.supplier_service.gateway.mysql.kyc.entity.SupplierKycEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class SubmitSupplierKycUseCase implements Usecase<SubmitSupplierKycUsecaseRequest, SubmitSupplierKycUsecaseResponse> {

    private final CreateSupplierService createSupplierService;
    private final SupplierKycGateway supplierKycGateway;
    private final OutboxService outboxService;

    public SubmitSupplierKycUseCase(
            CreateSupplierService createSupplierService,
            SupplierKycGateway supplierKycGateway,
            OutboxService outboxService
    ) {
        this.createSupplierService = createSupplierService;
        this.supplierKycGateway = supplierKycGateway;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public SubmitSupplierKycUsecaseResponse execute(SubmitSupplierKycUsecaseRequest request) {
        var supplier = createSupplierService.getRequired(request.supplierId());

        supplier.markKycSubmitted();
        createSupplierService.save(supplier);

        var kyc = new SupplierKycEntity(supplier, request.documentKind(), request.documentUrl());
        var savedKyc = supplierKycGateway.save(kyc);

        outboxService.append(supplier.getId(), "SUPPLIER_KYC_SUBMITTED",
                new SupplierKycSubmittedEvent(supplier.getId(), savedKyc.getDocumentKind()));

        return new SubmitSupplierKycUsecaseResponse(
                supplier.getId(),
                savedKyc.getDocumentKind(),
                savedKyc.getDocumentUrl(),
                supplier.getOnboardingStatus()
        );
    }

    private record SupplierKycSubmittedEvent(java.util.UUID supplierId, String documentKind) {}
}
