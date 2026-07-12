package com.learning.supplier_service.core.usecase.kyc.get;

import com.learning.supplier_service.core.usecase.Usecase;
import com.learning.supplier_service.exception.KycNotFoundException;
import com.learning.supplier_service.gateway.SupplierKycGateway;
import org.springframework.stereotype.Component;

@Component
public class GetKycUseCase implements Usecase<GetKycUsecaseRequest, GetKycUsecaseResponse> {

    private final SupplierKycGateway supplierKycGateway;

    public GetKycUseCase(SupplierKycGateway supplierKycGateway) {
        this.supplierKycGateway = supplierKycGateway;
    }

    @Override
    public GetKycUsecaseResponse execute(GetKycUsecaseRequest request) {
        var kyc = supplierKycGateway.findBySupplierId(request.supplierId())
                .orElseThrow(() -> new KycNotFoundException(request.supplierId()));
        return new GetKycUsecaseResponse(kyc);
    }
}
