package com.learning.supplier_service.core.processor.supplier.get;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.supplier.GetSupplierUseCase;
import com.learning.supplier_service.core.usecase.supplier.GetSupplierUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class GetSupplierProcessor implements Processor<GetSupplierProcessorRequest, GetSupplierProcessorResponse> {

    private final GetSupplierUseCase getSupplierUseCase;

    public GetSupplierProcessor(GetSupplierUseCase getSupplierUseCase) {
        this.getSupplierUseCase = getSupplierUseCase;
    }

    @Override
    public GetSupplierProcessorResponse process(GetSupplierProcessorRequest request) {
        var response = getSupplierUseCase.execute(new GetSupplierUsecaseRequest(request.supplierId()));
        return new GetSupplierProcessorResponse(response.supplier());
    }
}
