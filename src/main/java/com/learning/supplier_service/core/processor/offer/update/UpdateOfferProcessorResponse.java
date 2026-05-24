package com.learning.supplier_service.core.processor.offer.update;

import com.learning.supplier_service.core.processor.ProcessorResponse;
import com.learning.supplier_service.gateway.mysql.supplierproduct.entity.SupplierProductEntity;

public record UpdateOfferProcessorResponse(SupplierProductEntity offer) implements ProcessorResponse {
}
