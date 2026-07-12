package com.learning.supplier_service.core.usecase.kyc.get;

import com.learning.supplier_service.core.usecase.UsecaseResponse;
import com.learning.supplier_service.gateway.mysql.kyc.entity.SupplierKycEntity;

public record GetKycUsecaseResponse(SupplierKycEntity kyc) implements UsecaseResponse {
}
