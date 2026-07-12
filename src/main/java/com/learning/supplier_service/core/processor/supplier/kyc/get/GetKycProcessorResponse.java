package com.learning.supplier_service.core.processor.supplier.kyc.get;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.kyc.entity.SupplierKycEntity;

public record GetKycProcessorResponse(SupplierKycEntity kyc) implements ProcessorResponse {
}
