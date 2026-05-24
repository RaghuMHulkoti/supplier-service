package com.learning.supplier_service.core.processor.offer.get;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;

public record GetOfferProcessorResponse(SupplierProductEntity offer) implements ProcessorResponse {
}
