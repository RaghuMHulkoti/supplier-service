package com.learning.supplier_service.core.processor.supplier.kyc.get;

import com.learning.supplier_service.core.processor.Processor;
import com.learning.supplier_service.core.usecase.kyc.get.GetKycUseCase;
import com.learning.supplier_service.core.usecase.kyc.get.GetKycUsecaseRequest;
import org.springframework.stereotype.Component;

@Component
public class GetKycProcessor implements Processor<GetKycProcessorRequest, GetKycProcessorResponse> {

    private final GetKycUseCase getKycUseCase;

    public GetKycProcessor(GetKycUseCase getKycUseCase) {
        this.getKycUseCase = getKycUseCase;
    }

    @Override
    public GetKycProcessorResponse process(GetKycProcessorRequest request) {
        var response = getKycUseCase.execute(new GetKycUsecaseRequest(request.supplierId()));
        return new GetKycProcessorResponse(response.kyc());
    }
}
