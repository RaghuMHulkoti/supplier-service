package com.learning.supplier_service.core.processor.supplier.kyc.submit;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.supplier.SubmitSupplierKycUseCase;
import com.learning.supplier_service.core.usecase.supplier.SubmitSupplierKycUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class SubmitKycProcessor implements Processor<SubmitKycProcessorRequest, SubmitKycProcessorResponse> {

    private final SubmitSupplierKycUseCase submitSupplierKycUseCase;

    public SubmitKycProcessor(SubmitSupplierKycUseCase submitSupplierKycUseCase) {
        this.submitSupplierKycUseCase = submitSupplierKycUseCase;
    }

    @Override
    public SubmitKycProcessorResponse process(SubmitKycProcessorRequest request) {
        var response = submitSupplierKycUseCase.execute(new SubmitSupplierKycUsecaseRequest(
                request.getSupplierId(),
                request.getDocumentKind(),
                request.getDocumentUrl()
        ));
        return new SubmitKycProcessorResponse(
                response.supplierId(),
                response.documentKind(),
                response.documentUrl(),
                response.onboardingStatus()
        );
    }
}
