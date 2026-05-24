package com.learning.supplier_service.core.processor.supplier.status;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.supplier.status.UpdateSupplierStatusUseCase;
import com.learning.supplier_service.core.usecase.supplier.status.UpdateSupplierStatusUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateSupplierStatusProcessor implements Processor<UpdateSupplierStatusProcessorRequest, UpdateSupplierStatusProcessorResponse> {

    private final UpdateSupplierStatusUseCase updateSupplierStatusUseCase;

    public UpdateSupplierStatusProcessor(UpdateSupplierStatusUseCase updateSupplierStatusUseCase) {
        this.updateSupplierStatusUseCase = updateSupplierStatusUseCase;
    }

    @Override
    public UpdateSupplierStatusProcessorResponse process(UpdateSupplierStatusProcessorRequest request) {
        var response = updateSupplierStatusUseCase.execute(
                new UpdateSupplierStatusUsecaseRequest(request.getSupplierId(), request.getNewStatus())
        );
        return new UpdateSupplierStatusProcessorResponse(response.supplierId(), response.onboardingStatus());
    }
}
