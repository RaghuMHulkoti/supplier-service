package com.learning.supplier_service.core.usecase.supplier.status;

import com.learning.supplier_service.core.service.outbox.OutboxService;
import com.learning.supplier_service.core.service.supplier.create.CreateSupplierService;
import com.learning.supplier_service.core.usecase.Usecase;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class UpdateSupplierStatusUseCase implements Usecase<UpdateSupplierStatusUsecaseRequest, UpdateSupplierStatusUsecaseResponse> {

    private final CreateSupplierService createSupplierService;
    private final OutboxService outboxService;

    public UpdateSupplierStatusUseCase(CreateSupplierService createSupplierService, OutboxService outboxService) {
        this.createSupplierService = createSupplierService;
        this.outboxService = outboxService;
    }

    @Override
    @Transactional
    public UpdateSupplierStatusUsecaseResponse execute(UpdateSupplierStatusUsecaseRequest request) {
        var supplier = createSupplierService.getRequired(request.supplierId());
        supplier.updateStatus(request.newStatus());
        var saved = createSupplierService.save(supplier);
        outboxService.append(saved.getId(), "SUPPLIER_STATUS_UPDATED",
                new SupplierStatusUpdatedEvent(saved.getId(), saved.getOnboardingStatus().name()));
        return new UpdateSupplierStatusUsecaseResponse(saved.getId(), saved.getOnboardingStatus());
    }

    private record SupplierStatusUpdatedEvent(java.util.UUID supplierId, String status) {}
}
