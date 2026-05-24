package com.learning.supplier_service.core.processor.supplier.update;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.supplier.update.UpdateSupplierUseCase;
import com.learning.supplier_service.core.usecase.supplier.update.UpdateSupplierUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class UpdateSupplierProcessor implements Processor<UpdateSupplierProcessorRequest, UpdateSupplierProcessorResponse> {

    private final UpdateSupplierUseCase updateSupplierUseCase;

    public UpdateSupplierProcessor(UpdateSupplierUseCase updateSupplierUseCase) {
        this.updateSupplierUseCase = updateSupplierUseCase;
    }

    @Override
    public UpdateSupplierProcessorResponse process(UpdateSupplierProcessorRequest request) {
        var response = updateSupplierUseCase.execute(new UpdateSupplierUsecaseRequest(
                request.getSupplierId(),
                request.getLegalName(),
                request.getDisplayName(),
                request.getPhone(),
                request.getTaxId(),
                request.getCommissionPct()
        ));
        return new UpdateSupplierProcessorResponse(response.supplier());
    }
}
